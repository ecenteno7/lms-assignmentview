package org.lms.assignmentview.presentation.rest;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.application.ChatterApplicationService;
import org.lms.assignmentview.application.MessageView;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.presentation.websocket.dto.MessagesDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ChatterReadController {

    @NonNull
    private final ChatterApplicationService chatterApplicationService;

    @GetMapping("/api/courses/{course-id}/chatter")
    public @NonNull MessagesDto getCourseMessages(
            @PathVariable("course-id") @NonNull final String courseIdStr
    ) {
        final CourseId courseId = new CourseId(courseIdStr);
        final List<MessageView> messageViews = chatterApplicationService.getCourseMessages(courseId);
        return MessagesDto.from(messageViews);
    }

}
