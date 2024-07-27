package org.lms.assignmentview.presentation.websocket;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.application.ChatterApplicationService;
import org.lms.assignmentview.application.MessageView;
import org.lms.assignmentview.domain.chatter.commands.CreateMessageCommand;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.presentation.websocket.dto.MessageDto;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class ChatterController {

    @NonNull
    private final ChatterApplicationService chatterApplicationService;

    @MessageMapping("/chatter/{course-id}")
    @SendTo("/topic/chatter/{course-id}")
    public MessageDto chatter(@DestinationVariable("course-id") String courseIdStr, @Payload MessageDto messageDto) {
        final CourseId courseId = new CourseId(courseIdStr);
        final CreateMessageCommand createMessageCommand = messageDto.toCreateMessageCommand(courseId);
        final MessageView messageView = chatterApplicationService.createMessage(createMessageCommand);
        return MessageDto.from(messageView);
    }

}
