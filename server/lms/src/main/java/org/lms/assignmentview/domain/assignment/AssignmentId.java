package org.lms.assignmentview.domain.assignment;

import lombok.NonNull;

import java.util.UUID;

public record AssignmentId(@NonNull String id) {

    public static @NonNull AssignmentId createId() {
        return new AssignmentId(UUID.randomUUID().toString());
    }

}
