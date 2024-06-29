package org.lms.assignmentview.domain.tag;

import lombok.NonNull;

import java.util.UUID;

public record TagId(@NonNull String id) {

    public static @NonNull TagId createId() {
        return new TagId(UUID.randomUUID().toString());
    }

}
