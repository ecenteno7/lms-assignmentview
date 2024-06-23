package org.lms.assignmentview.domain.user;

import lombok.NonNull;
import org.lms.assignmentview.domain.course.CourseId;

public record User(
        @NonNull UserId userId,
        @NonNull CourseId classId
) {

    public static @NonNull User generateUser(@NonNull final CourseId classId) {
        return new User(UserId.generateId(), classId);
    }

}
