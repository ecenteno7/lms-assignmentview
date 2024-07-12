package org.lms.assignmentview.domain.assignment.commands;

import lombok.Builder;
import lombok.NonNull;
import org.lms.assignmentview.domain.user.User;

import java.time.OffsetDateTime;
import java.util.List;

@Builder
public record CreateAssignmentCommand(
        @NonNull User creator,
        @NonNull String title,
        @NonNull String description,
        @NonNull OffsetDateTime dueDate,
        @NonNull List<CreateModuleCommand> createModuleCommands
) {
}
