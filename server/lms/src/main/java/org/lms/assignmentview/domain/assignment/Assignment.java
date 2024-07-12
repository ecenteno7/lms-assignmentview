package org.lms.assignmentview.domain.assignment;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import org.lms.assignmentview.domain.assignment.commands.CreateAssignmentCommand;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.tag.Tag;
import org.lms.assignmentview.domain.tag.TagId;
import org.lms.assignmentview.domain.user.User;
import org.springframework.lang.Nullable;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.function.BiFunction;

@Value
@Builder(toBuilder = true)
public class Assignment {

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

    @NonNull
    private final String title;

    @NonNull
    private final String description;

    @NonNull
    private final OffsetDateTime dueDate;

    @NonNull
    @Builder.Default
    private final List<Module> modules = List.of();

    public static @NonNull Assignment createAssignment(@NonNull final CreateAssignmentCommand createAssignmentCommand,
                                                       @NonNull final BiFunction<CourseId, String, Tag> tagGenerator) {
        final Tag tag =
                tagGenerator.apply(createAssignmentCommand.creator().classId(), createAssignmentCommand.title());
        final AssignmentId generatedAssignmentId = AssignmentId.createId();
        return Assignment.builder()
                .assignmentId(generatedAssignmentId)
                .tagId(tag.getId())
                .creator(createAssignmentCommand.creator())
                .createdOn(OffsetDateTime.now())
                .title(createAssignmentCommand.title())
                .description(createAssignmentCommand.description())
                .dueDate(createAssignmentCommand.dueDate())
                .modules(createAssignmentCommand.createModuleCommands().stream()
                        .map(createModuleCommand ->
                                Module.createModule(createModuleCommand, generatedAssignmentId, tagGenerator))
                        .toList())
                .build();
    }

}
