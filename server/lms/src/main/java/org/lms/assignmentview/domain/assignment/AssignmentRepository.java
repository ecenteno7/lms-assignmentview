package org.lms.assignmentview.domain.assignment;

import lombok.NonNull;
import org.lms.assignmentview.domain.course.CourseId;

import java.util.List;
import java.util.Optional;

public interface AssignmentRepository {

    @NonNull List<Assignment> findAllByCourseId(@NonNull final CourseId courseId);

    @NonNull Optional<Assignment> findById(@NonNull final AssignmentId assignmentId);

    @NonNull List<Assignment> saveAll(@NonNull final List<Assignment> assignments);

}
