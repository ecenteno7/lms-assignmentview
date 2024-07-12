package org.lms.assignmentview.domain.assignment;

import lombok.NonNull;

import java.util.UUID;

public record ModuleId(@NonNull String id) {

    public static @NonNull ModuleId createId() {
        return new ModuleId(UUID.randomUUID().toString());
    }

}
