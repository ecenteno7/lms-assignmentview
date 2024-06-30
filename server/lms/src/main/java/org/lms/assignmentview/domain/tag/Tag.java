package org.lms.assignmentview.domain.tag;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.tag.command.CreateTagCommand;

@Value
@Builder(toBuilder = true)
public class Tag {

    @NonNull
    private final TagId id;

    @NonNull
    private final CourseId courseId;

    @NonNull
    private final String name;

    @NonNull
    private final String color;

    public static @NonNull Tag createTag(@NonNull final CreateTagCommand createTagCommand) {
        return Tag.builder()
                .id(TagId.createId())
                .courseId(createTagCommand.courseId())
                .name(createTagCommand.name())
                .color(createTagCommand.color())
                .build();
    }

}
