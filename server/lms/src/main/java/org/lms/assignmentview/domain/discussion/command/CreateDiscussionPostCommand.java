package org.lms.assignmentview.domain.discussion.command;

import org.lms.assignmentview.domain.tag.TagId;
import org.lms.assignmentview.domain.user.User;
import org.springframework.lang.NonNull;

import java.util.List;

public record CreateDiscussionPostCommand(
        @NonNull User author,
        @NonNull String title,
        @NonNull String content,
        @NonNull List<TagId> tagIds
) {
}
