package org.lms.assignmentview.application;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.domain.chatter.ChatterService;
import org.lms.assignmentview.domain.chatter.Message;
import org.lms.assignmentview.domain.chatter.commands.CreateMessageCommand;
import org.lms.assignmentview.domain.user.UserDetails;
import org.lms.assignmentview.domain.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
