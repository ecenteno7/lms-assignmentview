package org.lms.assignmentview.domain.discussion;

import lombok.NonNull;

import java.util.UUID;

public record DiscussionPostId(@NonNull String id) {

    public static DiscussionPostId createId() {
        return new DiscussionPostId(UUID.randomUUID().toString());
    }

}
