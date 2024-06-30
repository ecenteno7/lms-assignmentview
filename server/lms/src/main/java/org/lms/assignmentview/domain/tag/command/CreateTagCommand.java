package org.lms.assignmentview.domain.tag.command;

import lombok.Builder;
import lombok.NonNull;
import org.lms.assignmentview.domain.course.CourseId;

@Builder
public record CreateTagCommand(
        @NonNull CourseId courseId,
        @NonNull String name,
        @NonNull String color
) {
}
