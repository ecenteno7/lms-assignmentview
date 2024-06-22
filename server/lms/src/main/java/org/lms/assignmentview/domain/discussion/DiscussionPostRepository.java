package org.lms.assignmentview.domain.discussion;

import lombok.NonNull;

import java.util.Optional;

public interface DiscussionPostRepository {

    @NonNull
    Optional<DiscussionPost> findById(@NonNull final DiscussionPostId discussionPostId);

    @NonNull
    DiscussionPost save(@NonNull final DiscussionPost discussionPost);

}