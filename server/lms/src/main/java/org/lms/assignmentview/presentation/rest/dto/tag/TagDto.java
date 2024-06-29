package org.lms.assignmentview.presentation.rest.dto.tag;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.NonNull;
import org.lms.assignmentview.domain.tag.Tag;
import org.springframework.lang.Nullable;

@Builder
public record TagDto(
        @JsonProperty("tagID")
        @NonNull String tagId,

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

}
