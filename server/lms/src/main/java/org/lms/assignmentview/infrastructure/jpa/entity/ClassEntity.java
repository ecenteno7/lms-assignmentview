package org.lms.assignmentview.infrastructure.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import org.lms.assignmentview.domain.course.Course;
import org.lms.assignmentview.domain.course.CourseId;

@Getter
@NoArgsConstructor
@SuperBuilder
@Entity(name = "class")
public class ClassEntity {

    @Id
    private String id;

    @Column(name = "course_prefix")
    private String coursePrefix;

    @Column(name = "course_number")
    private int courseNumber;

    @Column(name = "course_name")
    private String courseName;

    public static @NonNull ClassEntity from(@NonNull final Course course) {
        return ClassEntity.builder()
                .id(course.getCourseId().id())
                .coursePrefix(course.getCoursePrefix())
                .courseNumber(course.getCourseNumber())
                .courseName(course.getCourseName())
                .build();
    }

    public @NonNull Course toDomain() {
        return Course.builder()
                .courseId(new CourseId(id))
                .coursePrefix(coursePrefix)
                .courseNumber(courseNumber)
                .courseName(courseName)
                .build();
    }

}
