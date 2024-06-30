package org.lms.assignmentview.domain.discussion;

import lombok.NonNull;
import org.lms.assignmentview.domain.user.User;
import org.lms.assignmentview.domain.user.UserDetails;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public record DiscussionPostsView(
        @NonNull List<DiscussionPost> discussionPosts,
        @NonNull Map<User, UserDetails> userDetailsByUserId,
        boolean displayResponses
) {

    public @NonNull UserDetails getUserDetailsFor(@NonNull final User userId) {
        return Optional.ofNullable(userDetailsByUserId.get(userId)).orElseThrow();
    }

}
