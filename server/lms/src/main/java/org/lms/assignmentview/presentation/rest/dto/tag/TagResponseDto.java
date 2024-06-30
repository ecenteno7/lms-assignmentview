package org.lms.assignmentview.presentation.rest.dto.tag;

import lombok.NonNull;
import org.lms.assignmentview.domain.tag.Tag;

import java.util.List;

public record TagResponseDto(
        @NonNull List<TagDto> tags
) {

    public static @NonNull TagResponseDto from(@NonNull final List<Tag> tags) {
        return new TagResponseDto(tags.stream()
                .map(TagDto::from)
                .toList());
    }

}
