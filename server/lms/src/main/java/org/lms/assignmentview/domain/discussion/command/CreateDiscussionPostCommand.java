package org.lms.assignmentview.domain.discussion.command;

import org.lms.assignmentview.domain.user.User;
import org.springframework.lang.NonNull;

public record CreateDiscussionPostCommand(
        @NonNull User author,
        @NonNull String title,
        @NonNull String content
) {
}
