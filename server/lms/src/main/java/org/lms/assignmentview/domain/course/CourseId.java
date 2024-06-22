package org.lms.assignmentview.domain.course;

import lombok.NonNull;

import java.util.UUID;

public record CourseId(@NonNull String id) {

    public static @NonNull CourseId generateId() {
        return new CourseId(UUID.randomUUID().toString());
    }

}
