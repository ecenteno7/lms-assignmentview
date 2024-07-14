package org.lms.assignmentview.application;

import lombok.NonNull;
import org.lms.assignmentview.domain.assignment.Assignment;
import org.lms.assignmentview.domain.discussion.DiscussionPost;
import org.lms.assignmentview.domain.user.User;
import org.lms.assignmentview.domain.user.UserDetails;

import java.util.List;
import java.util.Map;

public record AssignmentView(
        @NonNull Assignment assignment,
        boolean displayModules,
        @NonNull List<DiscussionPost> insights,
        @NonNull Map<User, UserDetails> userDetailsByUser
) {
}
