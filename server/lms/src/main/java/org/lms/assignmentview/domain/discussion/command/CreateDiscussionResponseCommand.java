package org.lms.assignmentview.domain.discussion.command;

import lombok.Builder;
import lombok.NonNull;
import org.lms.assignmentview.domain.discussion.DiscussionPostId;
import org.lms.assignmentview.domain.discussion.DiscussionResponseId;
import org.lms.assignmentview.domain.user.User;
import org.springframework.lang.Nullable;

@Builder
public record CreateDiscussionResponseCommand(
        @NonNull DiscussionPostId parentPostId,
        @Nullable DiscussionResponseId parentResponseId,
        @NonNull User author,
        @NonNull String content
) {
}
