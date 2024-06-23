package org.lms.assignmentview.domain.course.commands;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record CreateCourseCommand(
        @NonNull String prefix,
        int courseNumber,
        @NonNull String courseName
) {
}
