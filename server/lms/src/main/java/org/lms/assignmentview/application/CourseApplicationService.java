package org.lms.assignmentview.application;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.domain.course.Course;
import org.lms.assignmentview.domain.course.CourseService;
import org.lms.assignmentview.domain.course.commands.CreateCourseCommand;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CourseApplicationService {

    @NonNull
    private final CourseService courseService;

    @Transactional
    public @NonNull Course createCourse(@NonNull final CreateCourseCommand createCourseCommand) {
        return courseService.createCourse(createCourseCommand);
    }

}
