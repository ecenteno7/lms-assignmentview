package org.lms.assignmentview.application;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.domain.chatter.ChatterService;
import org.lms.assignmentview.domain.chatter.Message;
import org.lms.assignmentview.domain.chatter.commands.CreateMessageCommand;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.user.User;
import org.lms.assignmentview.domain.user.UserDetails;
import org.lms.assignmentview.domain.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ChatterApplicationService {

    @NonNull
    private final ChatterService chatterService;

    @NonNull
    private final UserService userService;

    @Transactional
    public @NonNull MessageView createMessage(@NonNull final CreateMessageCommand createMessageCommand) {
        final Message message = chatterService.createMessage(createMessageCommand);
        final UserDetails userDetails = userService.findByUser(message.getAuthor());
        return new MessageView(message, userDetails);
    }

    @Transactional(readOnly = true)
    public @NonNull List<MessageView> getCourseMessages(@NonNull final CourseId courseId) {
        final List<Message> messages = chatterService.getCourseMessages(courseId);
        final Set<User> users = messages.stream()
                .map(Message::getAuthor)
                .collect(Collectors.toSet());
        final Map<User, UserDetails> userDetailsByUser = userService.findByUsers(users);
        return messages.stream()
                .map(message -> new MessageView(message, userDetailsByUser.get(message.getAuthor())))
                .toList();
    }

}
