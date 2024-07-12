package org.lms.assignmentview.domain.assignment;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import org.lms.assignmentview.domain.assignment.commands.CreateModuleCommand;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.tag.Tag;
import org.lms.assignmentview.domain.tag.TagId;
import org.lms.assignmentview.domain.user.User;
import org.springframework.lang.Nullable;

import java.time.OffsetDateTime;
import java.util.function.BiFunction;

@Value
@Builder(toBuilder = true)
public class Module {

    @NonNull
    private final ModuleId moduleId;

    @NonNull
    private final AssignmentId assignmentId;

    @NonNull
    private final TagId tagId;

    @NonNull
    private final User creator;

    @NonNull
    private final OffsetDateTime createdOn;

    @Nullable
    private final OffsetDateTime updatedOn;

    private final int moduleNumber;

    @NonNull
    private final String title;

    @NonNull
    private final String description;

    public static @NonNull Module createModule(@NonNull final CreateModuleCommand createModuleCommand,
                                               @NonNull final AssignmentId assignmentId,
                                               @NonNull final BiFunction<CourseId, String, Tag> tagGenerator) {
        final Tag tag = tagGenerator.apply(createModuleCommand.user().classId(), createModuleCommand.title());
        return Module.builder()
                .moduleId(ModuleId.createId())
                .assignmentId(assignmentId)
                .tagId(tag.getId())
                .creator(createModuleCommand.user())
                .createdOn(OffsetDateTime.now())
                .moduleNumber(createModuleCommand.moduleNumber())
                .title(createModuleCommand.title())
                .description(createModuleCommand.description())
                .build();
    }

}
