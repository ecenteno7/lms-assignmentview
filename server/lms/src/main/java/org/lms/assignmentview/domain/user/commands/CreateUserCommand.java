package org.lms.assignmentview.domain.user.commands;

import lombok.Builder;
import lombok.NonNull;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.user.Role;

@Builder
public record CreateUserCommand(
        @NonNull String username,
        @NonNull String password,
        @NonNull String firstName,
        @NonNull String lastName,
        @NonNull CourseId classId,
        @NonNull Role role
) {
}
