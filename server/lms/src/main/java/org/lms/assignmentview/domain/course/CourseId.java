package org.lms.assignmentview.domain.course;

import lombok.NonNull;
import org.apache.commons.lang3.RandomStringUtils;

public record CourseId(@NonNull String id) {

    public static @NonNull CourseId generateId() {
        return new CourseId(RandomStringUtils.randomNumeric(16).toUpperCase());
    }

}
