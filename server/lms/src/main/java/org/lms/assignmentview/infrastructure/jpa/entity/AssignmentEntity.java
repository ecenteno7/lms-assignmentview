package org.lms.assignmentview.infrastructure.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import org.lms.assignmentview.domain.assignment.Assignment;
import org.lms.assignmentview.domain.assignment.AssignmentId;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.tag.TagId;
import org.lms.assignmentview.domain.user.User;
import org.lms.assignmentview.domain.user.UserId;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@SuperBuilder
@Entity(name = "assignment")
public class AssignmentEntity {

    @Id
    private UUID id;

    @Column(name = "tag_id", updatable = false)
    private UUID tagId;

    @Column(name = "creator", updatable = false)
    private String creator;

    @Column(name = "class_id", updatable = false)
    private String classId;

    @Column(name = "created_on", updatable = false)
    private OffsetDateTime createdOn;

    @Column(name = "updated_on")
    private OffsetDateTime updatedOn;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "due_date")
    private OffsetDateTime dueDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "assignmentId", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ModuleEntity> modules;

    public static @NonNull AssignmentEntity from(@NonNull final Assignment assignment) {
        return AssignmentEntity.builder()
                .id(UUID.fromString(assignment.getAssignmentId().id()))
                .tagId(UUID.fromString(assignment.getTagId().id()))
                .creator(assignment.getCreator().userId().id())
                .classId(assignment.getCreator().classId().id())
                .createdOn(assignment.getCreatedOn())
                .updatedOn(assignment.getUpdatedOn())
                .title(assignment.getTitle())
                .description(assignment.getDescription())
                .dueDate(assignment.getDueDate())
                .modules(assignment.getModules().stream()
                        .map(ModuleEntity::from)
                        .toList())
                .build();
    }

    public @NonNull Assignment toDomain() {
        return Assignment.builder()
                .assignmentId(new AssignmentId(id.toString()))
                .tagId(new TagId(tagId.toString()))
                .creator(new User(new UserId(creator), new CourseId(classId)))
                .createdOn(createdOn)
                .updatedOn(updatedOn)
                .title(title)
                .description(description)
                .dueDate(dueDate)
                .modules(modules.stream()
                        .map(ModuleEntity::toDomain)
                        .toList())
                .build();
    }

}
