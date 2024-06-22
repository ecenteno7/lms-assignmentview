package org.lms.assignmentview.presentation.rest.dto.course;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.NonNull;
import org.lms.assignmentview.domain.course.Course;
import org.lms.assignmentview.domain.course.commands.CreateCourseCommand;

@Builder
public record CourseDto(

        @JsonProperty("courseID")
        @Nullable String courseId,

        @NonNull String coursePrefix,

        int courseNumber,

        @NonNull String courseName
) {

    public static @NonNull CourseDto from(@NonNull final Course course) {
        return CourseDto.builder()
                .courseId(course.getCourseId().id())
                .coursePrefix(course.getCoursePrefix())
                .courseNumber(course.getCourseNumber())
                .courseName(course.getCourseName())
                .build();
    }

    public @NonNull CreateCourseCommand toCreateCourseCommand() {
        return CreateCourseCommand.builder()
                .prefix(coursePrefix)
                .courseNumber(courseNumber)
                .courseName(courseName)
                .build();
    }

}
