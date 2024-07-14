package org.lms.assignmentview.presentation.rest;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.application.AssignmentApplicationService;
import org.lms.assignmentview.application.AssignmentView;
import org.lms.assignmentview.domain.assignment.AssignmentId;
import org.lms.assignmentview.domain.assignment.commands.CreateAssignmentCommand;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.presentation.rest.dto.assignment.AssignmentsRootDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class AssignmentController {

    @NonNull
    private final AssignmentApplicationService assignmentApplicationService;

    @PostMapping("/api/courses/{course-id}/assignments")
    public @NonNull AssignmentsRootDto createAssignments(
            @PathVariable("course-id") @NonNull final String courseIdStr,
            @RequestBody @NonNull final AssignmentsRootDto assignmentsRequestDto
    ) {
        final CourseId courseId = new CourseId(courseIdStr);
        final List<CreateAssignmentCommand> createAssignmentCommands = assignmentsRequestDto.assignments().stream()
                .map(assignmentDto -> assignmentDto.toCreateAssignmentCommand(courseId))
                .toList();
        final List<AssignmentView> assignmentViews =
                assignmentApplicationService.createAssignments(createAssignmentCommands);
        return AssignmentsRootDto.from(assignmentViews);
    }

    @GetMapping("/api/courses/{course-id}/assignments")
    public @NonNull AssignmentsRootDto getCourseAssignments(
            @PathVariable("course-id") @NonNull final String courseIdStr
    ) {
        final CourseId courseId = new CourseId(courseIdStr);
        final List<AssignmentView> assignmentViews = assignmentApplicationService.getClassAssignments(courseId);
        return AssignmentsRootDto.from(assignmentViews);
    }

    @GetMapping("/api/courses/{course-id}/assignments/{assignment-id}")
    public @NonNull AssignmentsRootDto getAssignmentById(
            @PathVariable("course-id") @NonNull final String courseIdStr,
            @PathVariable("assignment-id") @NonNull final String assignmentIdStr
    ) {
        final CourseId courseId = new CourseId(courseIdStr);
        final AssignmentId assignmentId = new AssignmentId(assignmentIdStr);
        final AssignmentView assignmentView = assignmentApplicationService.getAssignmentById(assignmentId);
        return AssignmentsRootDto.from(List.of(assignmentView));
    }

}
