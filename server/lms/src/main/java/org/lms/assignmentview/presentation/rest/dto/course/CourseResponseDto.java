package org.lms.assignmentview.presentation.rest.dto.course;

import lombok.NonNull;
import org.lms.assignmentview.domain.course.CourseView;

import java.util.List;

public record CourseResponseDto(
        @NonNull List<CourseDto> courses
) {

    public static @NonNull CourseResponseDto from(@NonNull final List<CourseView> courses) {
        return new CourseResponseDto(courses.stream()
                .map(CourseDto::from)
                .toList());
    }

}
