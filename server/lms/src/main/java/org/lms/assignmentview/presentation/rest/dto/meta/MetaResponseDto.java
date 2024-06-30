package org.lms.assignmentview.presentation.rest.dto.meta;

import lombok.NonNull;
import org.lms.assignmentview.application.MetaResponseView;
import org.lms.assignmentview.presentation.rest.dto.tag.TagDto;

import java.util.List;

public record MetaResponseDto(
        @NonNull List<TagDto> availableTags
) {

    public static @NonNull MetaResponseDto from(@NonNull final MetaResponseView metaResponseView) {
        return new MetaResponseDto(metaResponseView.tags().stream()
                .map(TagDto::from)
                .toList());
    }

}
