package org.lms.assignmentview.domain.user.commands;

import lombok.NonNull;

public record UserLoginCommand(
        @NonNull String username,
        @NonNull String password
) {
}
