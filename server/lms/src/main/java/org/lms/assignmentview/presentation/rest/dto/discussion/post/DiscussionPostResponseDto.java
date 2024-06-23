package org.lms.assignmentview.presentation.rest.dto.discussion.post;


import lombok.NonNull;
import org.lms.assignmentview.domain.discussion.DiscussionPostView;

import java.util.List;

public record DiscussionPostResponseDto(
        @NonNull List<DiscussionPostDto> discussionPosts
) {

    public static @NonNull DiscussionPostResponseDto from(@NonNull final List<DiscussionPostView> discussionPostViews) {
        return new DiscussionPostResponseDto(discussionPostViews.stream()
                .map(DiscussionPostDto::from)
                .toList());
    }

}
