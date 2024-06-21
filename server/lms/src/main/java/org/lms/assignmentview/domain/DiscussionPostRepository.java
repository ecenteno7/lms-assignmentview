package org.lms.assignmentview.domain;

import lombok.NonNull;
import org.lms.assignmentview.domain.discussion.DiscussionPost;
import org.lms.assignmentview.domain.discussion.DiscussionPostId;

import java.util.Optional;

public interface DiscussionPostRepository {

    @NonNull Optional<DiscussionPost> findById(@NonNull final DiscussionPostId discussionPostId);

    @NonNull DiscussionPost save(@NonNull final DiscussionPost discussionPost);

}
