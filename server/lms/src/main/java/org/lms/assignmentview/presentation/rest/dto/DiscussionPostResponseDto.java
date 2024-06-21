package org.lms.assignmentview.presentation.rest.dto;

import org.springframework.lang.NonNull;

import java.util.List;

public record DiscussionPostResponseDto(
        @NonNull List<DiscussionPostDto> discussionPosts
) {
}
