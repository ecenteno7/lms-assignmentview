package org.lms.assignmentview.domain.course;

import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface ClassRepository {

    @NonNull
    List<Course> findAll();

    @NonNull
    Optional<Course> findById(@NonNull CourseId id);

    @NonNull
    Course save(@NonNull Course course);

}
