package org.lms.assignmentview.presentation.rest;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.application.CourseApplicationService;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.course.CourseView;
import org.lms.assignmentview.domain.course.commands.CreateCourseCommand;
import org.lms.assignmentview.presentation.rest.dto.course.CourseDto;
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
        final List<CreateCourseCommand> createCourseCommands = courseRequestDto.courses().stream()
                .map(CourseDto::toCreateCourseCommand)
                .toList();
        final List<CourseView> courseViews = courseApplicationService.createCourse(createCourseCommands);
        return CourseResponseDto.from(courseViews);
    }

    @GetMapping("/api/admin/courses")
    public @NonNull CourseResponseDto getAllCourses() {
        final List<CourseView> courses = courseApplicationService.getAllCourses();
        return CourseResponseDto.from(courses);
    }

    @GetMapping("/api/admin/courses/{course-id}")
    public @NonNull CourseResponseDto getCourseById(@PathVariable("course-id") @NonNull final String courseId) {
        final CourseView course = courseApplicationService.getCourseById(new CourseId(courseId));
        return CourseResponseDto.from(List.of(course));
    }

}
