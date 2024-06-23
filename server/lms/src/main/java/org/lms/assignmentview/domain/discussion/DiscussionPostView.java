package org.lms.assignmentview.domain.discussion;

import lombok.NonNull;
import org.lms.assignmentview.domain.user.UserDetails;

public record DiscussionPostView(
        @NonNull DiscussionPost discussionPost,
        @NonNull UserDetails userDetails
) {
}
