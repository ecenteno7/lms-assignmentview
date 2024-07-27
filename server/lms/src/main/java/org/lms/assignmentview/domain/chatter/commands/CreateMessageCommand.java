package org.lms.assignmentview.domain.chatter.commands;

import lombok.NonNull;
import org.lms.assignmentview.domain.user.User;

public record CreateMessageCommand(
        @NonNull User author,
        @NonNull String content
) {
}
