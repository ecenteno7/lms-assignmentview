package org.lms.assignmentview.presentation.rest;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.application.MetaApplicationService;
import org.lms.assignmentview.application.MetaResponseView;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.presentation.rest.dto.meta.MetaResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MetaController {

    @NonNull
    private final MetaApplicationService metaApplicationService;

    @GetMapping("/api/courses/{course-id}/meta")
    public @NonNull MetaResponseDto getCourseMeta(
            @PathVariable("course-id") @NonNull final String courseIdStr
    ) {
        final CourseId courseId = new CourseId(courseIdStr);
        final MetaResponseView metaResponseView = metaApplicationService.getCourseMeta(courseId);
        return MetaResponseDto.from(metaResponseView);
    }

}
