package org.lms.assignmentview.presentation.rest.dto.discussion.post;

import lombok.NonNull;

import java.util.List;

public record DiscussionPostRequestDto(
        @NonNull List<DiscussionPostDto> discussionPosts
) {
}
