package org.lms.assignmentview.domain.course;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.domain.course.commands.CreateCourseCommand;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseService {

    @NonNull
    private final ClassRepository classRepository;

    public @NonNull List<Course> createCourses(@NonNull final List<CreateCourseCommand> createCourseCommands) {
        final List<Course> courses = createCourseCommands.stream()
                .map(Course::from)
                .toList();
        return classRepository.saveAll(courses);
    }

    public @NonNull Course getCourseById(@NonNull final CourseId courseId) {
        return classRepository.findById(courseId).orElseThrow();
    }

    public @NonNull List<Course> getAllCourses() {
        return classRepository.findAll();
    }

}
