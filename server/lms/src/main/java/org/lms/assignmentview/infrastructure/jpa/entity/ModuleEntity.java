package org.lms.assignmentview.infrastructure.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import org.lms.assignmentview.domain.assignment.AssignmentId;
import org.lms.assignmentview.domain.assignment.Module;
import org.lms.assignmentview.domain.assignment.ModuleId;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.tag.TagId;
import org.lms.assignmentview.domain.user.User;
import org.lms.assignmentview.domain.user.UserId;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
@SuperBuilder
@Entity(name = "module")
public class ModuleEntity {

    @Id
    private UUID id;

    @Column(name = "assignment_id", updatable = false)
    private UUID assignmentId;

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

    @Column(name = "module_number")
    private int moduleNumber;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    public static @NonNull ModuleEntity from(@NonNull final Module module) {
        return ModuleEntity.builder()
                .id(UUID.fromString(module.getModuleId().id()))
                .assignmentId(UUID.fromString(module.getAssignmentId().id()))
                .tagId(UUID.fromString(module.getTagId().id()))
                .creator(module.getCreator().userId().id())
                .classId(module.getCreator().classId().id())
                .createdOn(module.getCreatedOn())
                .updatedOn(module.getUpdatedOn())
                .moduleNumber(module.getModuleNumber())
                .title(module.getTitle())
                .description(module.getDescription())
                .build();
    }

    public @NonNull Module toDomain() {
        return Module.builder()
                .moduleId(new ModuleId(id.toString()))
                .assignmentId(new AssignmentId(assignmentId.toString()))
                .tagId(new TagId(tagId.toString()))
                .creator(new User(new UserId(creator), new CourseId(classId)))
                .createdOn(createdOn)
                .updatedOn(updatedOn)
                .moduleNumber(moduleNumber)
                .title(title)
                .description(description)
                .build();
    }

}
