package org.lms.assignmentview.presentation.rest;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.application.CourseApplicationService;
import org.lms.assignmentview.domain.course.Course;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.course.commands.CreateCourseCommand;
import org.lms.assignmentview.presentation.rest.dto.course.CourseRequestDto;
import org.lms.assignmentview.presentation.rest.dto.course.CourseResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CourseController {

    @NonNull
    private final CourseApplicationService courseApplicationService;

    @PostMapping("/api/admin/courses")
    public @NonNull CourseResponseDto createCourse(@RequestBody final CourseRequestDto courseRequestDto) {
        if (courseRequestDto.courses().size() != 1) {
            throw new IllegalStateException("Application only supports adding a single course at a time.");
        }
        final CreateCourseCommand createCourseCommand = courseRequestDto.courses().get(0).toCreateCourseCommand();
        final Course course = courseApplicationService.createCourse(createCourseCommand);
        return CourseResponseDto.from(List.of(course));
    }

    @GetMapping("/api/admin/courses")
    public @NonNull CourseResponseDto getAllCourses() {
        final List<Course> courses = courseApplicationService.getAllCourses();
        return CourseResponseDto.from(courses);
    }

    @GetMapping("/api/admin/courses/{course-id}")
    public @NonNull CourseResponseDto getCourseById(@PathVariable("course-id") @NonNull final String courseId) {
        final Course course = courseApplicationService.getCourseById(new CourseId(courseId));
        return CourseResponseDto.from(List.of(course));
    }

}
