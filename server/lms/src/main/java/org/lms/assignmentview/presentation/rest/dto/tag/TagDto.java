package org.lms.assignmentview.presentation.rest.dto.tag;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.NonNull;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.tag.Tag;
import org.lms.assignmentview.domain.tag.command.CreateTagCommand;
import org.springframework.lang.Nullable;

import java.util.Objects;

@Builder
public record TagDto(
        @JsonProperty("tagID")
        @Nullable String tagId,

        @Nullable String name,

        @Nullable String color
) {

    public static @NonNull TagDto from(@NonNull final Tag tag) {
        return TagDto.builder()
                .tagId(tag.getId().id())
                .name(tag.getName())
                .color(tag.getColor())
                .build();
    }

    public @NonNull CreateTagCommand toCommand(@NonNull final CourseId courseId) {
        if (Objects.isNull(name) || Objects.isNull(color)) {
            throw new IllegalArgumentException("Name and color are required to create a tag.");
        }
        return CreateTagCommand.builder()
                .courseId(courseId)
                .name(name)
                .color(color)
                .build();
    }

}
