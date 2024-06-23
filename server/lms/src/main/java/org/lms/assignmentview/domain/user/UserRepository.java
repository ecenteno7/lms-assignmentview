package org.lms.assignmentview.domain.user;

import lombok.NonNull;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.user.commands.UserLoginCommand;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    @NonNull
    List<AdminUserDetails> findAllByCourseId(@NonNull final CourseId courseId);

    @NonNull
    Optional<UserDetails> findByUser(@NonNull final User user);

    @NonNull
    Optional<UserDetails> login(@NonNull final UserLoginCommand userLoginCommand);

    @NonNull
    List<AdminUserDetails> saveAll(@NonNull final List<AdminUserDetails> userDetailsList);
}
