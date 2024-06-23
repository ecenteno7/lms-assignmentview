package org.lms.assignmentview.domain.user;

import lombok.Builder;
import lombok.NonNull;
import org.lms.assignmentview.domain.course.Course;

@Builder
public record UserLoginView(
        @NonNull UserDetails userDetails,
        @NonNull Course course
) {
}
