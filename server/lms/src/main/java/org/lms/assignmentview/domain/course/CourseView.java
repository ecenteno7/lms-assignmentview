package org.lms.assignmentview.domain.course;

import lombok.Builder;
import lombok.NonNull;
import org.lms.assignmentview.domain.user.UserDetails;

import java.util.List;

@Builder
public record CourseView(
        @NonNull Course course,
        long numberOfStudents,
        long numberOfStaff
) {

    public static @NonNull CourseView from(@NonNull final Course course, @NonNull final List<UserDetails> courseUsers) {
        final long numberOfStaff = courseUsers.stream()
                .filter(UserDetails::isStaff)
                .count();
        final long numberOfStudents = courseUsers.size() - numberOfStaff;
        return new CourseView(course, numberOfStudents, numberOfStaff);
    }

}
