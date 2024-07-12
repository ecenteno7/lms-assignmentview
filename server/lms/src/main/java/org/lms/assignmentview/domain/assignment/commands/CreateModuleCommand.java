package org.lms.assignmentview.domain.assignment.commands;

import lombok.Builder;
import org.lms.assignmentview.domain.user.User;
import org.springframework.lang.NonNull;

@Builder
public record CreateModuleCommand(
        @NonNull User user,
        int moduleNumber,
        @NonNull String title,
        @NonNull String description
) {
}
