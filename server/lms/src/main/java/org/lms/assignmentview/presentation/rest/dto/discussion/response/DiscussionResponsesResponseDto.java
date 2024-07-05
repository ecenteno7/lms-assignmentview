package org.lms.assignmentview.presentation.rest.dto.discussion.response;

import lombok.NonNull;
import org.lms.assignmentview.domain.discussion.DiscussionResponse;

import java.util.List;

public record DiscussionResponsesResponseDto(
        @NonNull List<DiscussionResponseDto> responses
) {

    public static @NonNull DiscussionResponsesResponseDto from(@NonNull final List<DiscussionResponse> discussionResponses) {
        return new DiscussionResponsesResponseDto(discussionResponses.stream()
                .map(DiscussionResponseDto::from)
                .toList());
    }

}
