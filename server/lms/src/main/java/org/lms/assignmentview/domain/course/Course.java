package org.lms.assignmentview.domain.course;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import org.lms.assignmentview.domain.course.commands.CreateCourseCommand;

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

    public static @NonNull Course from(@NonNull final CreateCourseCommand createCourseCommand) {
        return Course.builder()
                .courseId(CourseId.generateId())
                .coursePrefix(createCourseCommand.prefix())
                .courseNumber(createCourseCommand.courseNumber())
                .courseName(createCourseCommand.courseName())
                .build();
    }
}
