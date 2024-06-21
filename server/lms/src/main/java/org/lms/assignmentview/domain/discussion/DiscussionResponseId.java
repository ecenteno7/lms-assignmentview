package org.lms.assignmentview.domain.discussion;

import lombok.NonNull;

import java.util.UUID;

public record DiscussionResponseId(@NonNull String id) {

    public static DiscussionResponseId createId() {
        return new DiscussionResponseId(UUID.randomUUID().toString());
    }

}
