package org.lms.assignmentview.infrastructure.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.tag.Tag;
import org.lms.assignmentview.domain.tag.TagId;

import java.util.UUID;

@Getter
@NoArgsConstructor
@SuperBuilder
@Entity(name = "tag")
public class TagEntity {

    @Id
    private UUID id;

    @Column(name = "class_id", updatable = false)
    private String classId;

    @Column(name = "name")
    private String name;

    @Column(name = "color")
    private String color;

    public static @NonNull TagEntity from(@NonNull final Tag tag) {
        return TagEntity.builder()
                .id(UUID.fromString(tag.getId().id()))
                .classId(tag.getCourseId().id())
                .name(tag.getName())
                .color(tag.getColor())
                .build();
    }

    public @NonNull Tag toDomain() {
        return Tag.builder()
                .id(new TagId(id.toString()))
                .courseId(new CourseId(classId))
                .name(name)
                .color(color)
                .build();
    }

}
