package org.lms.assignmentview.domain.user;

import lombok.NonNull;

public record User(
        @NonNull String userId,
        @NonNull String classId
) {
}
