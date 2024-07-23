package org.lms.assignmentview.domain.tag.command;

import lombok.Builder;
import lombok.NonNull;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.tag.TagId;
import org.springframework.lang.Nullable;

@Builder
public record CreateTagCommand(
        @NonNull CourseId courseId,
        @NonNull String name,
        @NonNull String color,
        @Nullable TagId parentTagId
) {

    public static @NonNull CreateTagCommand from(@NonNull final CourseId courseId,
                                                 @NonNull final String name,
                                                 @Nullable final TagId parentTagId) {
        return new CreateTagCommand(courseId, name, "#FFFFFF", parentTagId);
    }

}
