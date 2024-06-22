package org.lms.assignmentview.domain.course;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Course {

    @NonNull
    private final CourseId courseId;

    @NonNull
    private final String coursePrefix;

    private final int courseNumber;

    @NonNull
    private final String courseName;
}
