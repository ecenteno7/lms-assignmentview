package org.lms.assignmentview.domain.user;

import lombok.NonNull;
import org.apache.commons.lang3.RandomStringUtils;

public record UserId(@NonNull String id) {

    public static @NonNull UserId generateId() {
        return new UserId(RandomStringUtils.randomAlphabetic(16).toUpperCase());
    }

}
