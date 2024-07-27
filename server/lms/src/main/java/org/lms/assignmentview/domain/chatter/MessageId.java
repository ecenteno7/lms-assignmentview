package org.lms.assignmentview.domain.chatter;

import lombok.NonNull;

import java.util.UUID;

public record MessageId(@NonNull String id) {

    public static @NonNull MessageId generateId() {
        return new MessageId(UUID.randomUUID().toString());
    }

}
