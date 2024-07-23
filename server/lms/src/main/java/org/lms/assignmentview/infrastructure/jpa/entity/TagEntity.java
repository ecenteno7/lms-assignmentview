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
import org.springframework.lang.Nullable;

import java.util.Optional;
import java.util.UUID;

@Getter
@NoArgsConstructor
@SuperBuilder
@Entity(name = "tag")
public class TagEntity {

    @Id
    @NonNull
    private UUID id;

    @Column(name = "class_id", updatable = false)
    @NonNull
    private String classId;

    @Column(name = "name")
    @NonNull
    private String name;

    @Column(name = "color")
    @NonNull
    private String color;

    @Column(name = "parent_tag_id")
    @Nullable
    private UUID parentTagId;

    public static @NonNull TagEntity from(@NonNull final Tag tag) {
        return TagEntity.builder()
                .id(UUID.fromString(tag.getId().id()))
                .classId(tag.getCourseId().id())
                .name(tag.getName())
                .color(tag.getColor())
                .parentTagId(tag.getParentTagId()
                        .map(tagId -> UUID.fromString(tagId.id()))
                        .orElse(null))
                .build();
    }

    public @NonNull Tag toDomain() {
        return Tag.builder()
                .id(new TagId(id.toString()))
                .courseId(new CourseId(classId))
                .name(name)
                .color(color)
                .parentTagId(Optional.ofNullable(parentTagId)
                        .map(tagId -> new TagId(tagId.toString()))
                        .orElse(null))
                .build();
    }

}
