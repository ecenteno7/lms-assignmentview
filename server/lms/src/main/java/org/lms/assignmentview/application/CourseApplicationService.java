package org.lms.assignmentview.application;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.domain.course.Course;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.course.CourseService;
import org.lms.assignmentview.domain.course.CourseView;
import org.lms.assignmentview.domain.course.commands.CreateCourseCommand;
import org.lms.assignmentview.domain.user.AdminUserDetails;
import org.lms.assignmentview.domain.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseApplicationService {

    @NonNull
    private final CourseService courseService;

    @NonNull
    private final UserService userService;

    @Transactional
    public @NonNull List<CourseView> createCourse(@NonNull final List<CreateCourseCommand> createCourseCommand) {
        return courseService.createCourses(createCourseCommand).stream()
                .map(course -> new CourseView(course, 0, 0))
                .toList();
    }

    @Transactional(readOnly = true)
    public @NonNull List<CourseView> getAllCourses() {
        final List<Course> courses = courseService.getAllCourses();
        return courses.stream()
                .map(course -> {
                    final List<AdminUserDetails> courseUsers = userService.getUsersForCourse(course.getCourseId());
                    return CourseView.from(course, courseUsers);
                })
                .toList();
    }

    @Transactional(readOnly = true)
    public @NonNull CourseView getCourseById(@NonNull final CourseId courseId) {
        final Course course = courseService.getCourseById(courseId);
        final List<AdminUserDetails> users = userService.getUsersForCourse(course.getCourseId());
        return CourseView.from(course, users);
    }

}
