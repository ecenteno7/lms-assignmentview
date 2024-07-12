package org.lms.assignmentview.domain.assignment;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.domain.assignment.commands.CreateAssignmentCommand;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.tag.Tag;
import org.lms.assignmentview.domain.tag.TagService;
import org.lms.assignmentview.domain.tag.command.CreateTagCommand;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.BiFunction;

@AllArgsConstructor
@Service
public class AssignmentService {

    @NonNull
    private final AssignmentRepository assignmentRepository;

    @NonNull
    private final TagService tagService;

    public @NonNull List<Assignment> createAssignments(
            @NonNull final List<CreateAssignmentCommand> createAssignmentCommands
    ) {
        final List<Assignment> assignments = createAssignmentCommands.stream()
                .map(this::createAssignment)
                .toList();
        return assignmentRepository.saveAll(assignments);
    }

    public @NonNull List<Assignment> getClassAssignments(@NonNull final CourseId courseId) {
        return assignmentRepository.findAllByCourseId(courseId);
    }

    public @NonNull Assignment getAssignmentById(@NonNull final AssignmentId assignmentId) {
        return assignmentRepository.findById(assignmentId).orElseThrow();
    }

    private @NonNull Assignment createAssignment(@NonNull final CreateAssignmentCommand createAssignmentCommand) {
        final BiFunction<CourseId, String, Tag> tagGenerator = (classId, title) -> {
            final CreateTagCommand createTagCommand =
                    new CreateTagCommand(createAssignmentCommand.creator().classId(), createAssignmentCommand.title(), "#FFFFFF");
            return tagService.createTags(List.of(createTagCommand)).get(0);
        };
        return Assignment.createAssignment(createAssignmentCommand, tagGenerator);
    }

}
