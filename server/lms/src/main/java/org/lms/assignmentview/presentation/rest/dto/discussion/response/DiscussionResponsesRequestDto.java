package org.lms.assignmentview.presentation.rest.dto.discussion.response;

import lombok.NonNull;

import java.util.List;

public record DiscussionResponsesRequestDto(
        @NonNull List<DiscussionResponseDto> responses
) {
}
