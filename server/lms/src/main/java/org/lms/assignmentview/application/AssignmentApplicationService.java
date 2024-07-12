package org.lms.assignmentview.application;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.domain.assignment.Assignment;
import org.lms.assignmentview.domain.assignment.AssignmentId;
import org.lms.assignmentview.domain.assignment.AssignmentService;
import org.lms.assignmentview.domain.assignment.commands.CreateAssignmentCommand;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.discussion.DiscussionPost;
import org.lms.assignmentview.domain.discussion.DiscussionPostService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class AssignmentApplicationService {

    @NonNull
    private final AssignmentService assignmentService;

    @NonNull
    private final DiscussionPostService discussionPostService;

    @Transactional
    public @NonNull List<AssignmentView> createAssignments(
            @NonNull final List<CreateAssignmentCommand> createAssignmentCommands
    ) {
        return assignmentService.createAssignments(createAssignmentCommands).stream()
                .map(assignment -> new AssignmentView(assignment, true, List.of()))
                .toList();
    }

    @Transactional(readOnly = true)
    public @NonNull List<AssignmentView> getClassAssignments(@NonNull final CourseId courseId) {
        return assignmentService.getClassAssignments(courseId).stream()
                .map(assignment -> new AssignmentView(assignment, false, List.of()))
                .toList();
    }

    @Transactional(readOnly = true)
    public @NonNull AssignmentView getAssignmentById(@NonNull final AssignmentId assignmentId) {
        final Assignment assignment = assignmentService.getAssignmentById(assignmentId);
        final List<DiscussionPost> discussionPosts = discussionPostService.getAllByTagId(assignment.getTagId());
        return new AssignmentView(assignment, true, discussionPosts);
    }

}
