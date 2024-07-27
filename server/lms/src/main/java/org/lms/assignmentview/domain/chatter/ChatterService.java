package org.lms.assignmentview.domain.chatter;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.domain.chatter.commands.CreateMessageCommand;
import org.lms.assignmentview.domain.course.CourseId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ChatterService {

    @NonNull
    private final MessageRepository messageRepository;

    public @NonNull Message createMessage(@NonNull final CreateMessageCommand createMessageCommand) {
        final Message message = Message.from(createMessageCommand);
        return messageRepository.save(message);
    }

    public @NonNull List<Message> getCourseMessages(@NonNull final CourseId courseId) {
        return messageRepository.getMessagesByCourseId(courseId);
    }

}
