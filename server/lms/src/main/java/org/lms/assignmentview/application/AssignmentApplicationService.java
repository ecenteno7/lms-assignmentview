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
import org.lms.assignmentview.domain.user.User;
import org.lms.assignmentview.domain.user.UserDetails;
import org.lms.assignmentview.domain.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AssignmentApplicationService {

    @NonNull
    private final AssignmentService assignmentService;

    @NonNull
    private final DiscussionPostService discussionPostService;

    @NonNull
    private final UserService userService;

    @Transactional
    public @NonNull List<AssignmentView> createAssignments(
            @NonNull final List<CreateAssignmentCommand> createAssignmentCommands
    ) {
        return assignmentService.createAssignments(createAssignmentCommands).stream()
                .map(assignment -> new AssignmentView(assignment, true, List.of(), Map.of()))
                .toList();
    }

    @Transactional(readOnly = true)
    public @NonNull List<AssignmentView> getClassAssignments(@NonNull final CourseId courseId) {
        return assignmentService.getClassAssignments(courseId).stream()
                .map(assignment -> new AssignmentView(assignment, false, List.of(), Map.of()))
                .toList();
    }

    @Transactional(readOnly = true)
    public @NonNull AssignmentView getAssignmentById(@NonNull final AssignmentId assignmentId) {
        final Assignment assignment = assignmentService.getAssignmentById(assignmentId);
        final List<DiscussionPost> discussionPosts = discussionPostService.getDiscussionInsights(assignment.getTagId());
        final Set<User> discussionPostUsers = discussionPosts.stream()
                .flatMap(DiscussionPost::getInvolvedUsers)
                .collect(Collectors.toSet());
        final Map<User, UserDetails> userDetailsByUser = userService.findByUsers(discussionPostUsers);
        return new AssignmentView(assignment, true, discussionPosts, userDetailsByUser);
    }

}
