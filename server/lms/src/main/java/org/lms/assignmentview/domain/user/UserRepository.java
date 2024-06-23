package org.lms.assignmentview.domain.user;

import lombok.NonNull;
import org.lms.assignmentview.domain.course.CourseId;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    @NonNull
    List<UserDetails> findAll();

    @NonNull
    List<UserDetails> findAllByCourseId(@NonNull final CourseId courseId);

    @NonNull
    Optional<UserDetails> findByUser(@NonNull final User user);

    @NonNull
    UserDetails save(@NonNull final UserDetails userDetails);

    @NonNull
    List<UserDetails> saveAll(@NonNull final List<UserDetails> userDetailsList);
}
