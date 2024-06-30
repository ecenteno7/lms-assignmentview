package org.lms.assignmentview.presentation.rest.dto.discussion.post;


import lombok.NonNull;
import org.lms.assignmentview.domain.discussion.DiscussionPostsView;

import java.util.List;

public record DiscussionPostResponseDto(
        @NonNull List<DiscussionPostDto> discussionPosts
) {

    public static @NonNull DiscussionPostResponseDto from(@NonNull final DiscussionPostsView discussionPostsView) {
        return new DiscussionPostResponseDto(discussionPostsView.discussionPosts().stream()
                .map(discussionPost -> DiscussionPostDto.from(discussionPost, discussionPostsView))
                .toList());
    }

}
