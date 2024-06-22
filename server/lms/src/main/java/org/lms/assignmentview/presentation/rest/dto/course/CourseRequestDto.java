package org.lms.assignmentview.presentation.rest.dto.course;

import lombok.NonNull;

import java.util.List;

public record CourseRequestDto(
        @NonNull List<CourseDto> courses
) {
}
