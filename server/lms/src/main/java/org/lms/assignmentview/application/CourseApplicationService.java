package org.lms.assignmentview.application;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.domain.course.Course;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.course.CourseService;
import org.lms.assignmentview.domain.course.commands.CreateCourseCommand;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseApplicationService {

    @NonNull
    private final CourseService courseService;

    @Transactional
    public @NonNull Course createCourse(@NonNull final CreateCourseCommand createCourseCommand) {
        return courseService.createCourse(createCourseCommand);
    }

    @Transactional(readOnly = true)
    public @NonNull List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @Transactional(readOnly = true)
    public @NonNull Course getCourseById(@NonNull final CourseId courseId) {
        return courseService.getCourseById(courseId);
    }

}
