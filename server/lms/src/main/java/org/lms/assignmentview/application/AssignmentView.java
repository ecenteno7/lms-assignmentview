package org.lms.assignmentview.application;

import lombok.NonNull;
import org.lms.assignmentview.domain.assignment.Assignment;
import org.lms.assignmentview.domain.discussion.DiscussionPost;

import java.util.List;

public record AssignmentView(
        @NonNull Assignment assignment,
        boolean displayModules,
        @NonNull List<DiscussionPost> insights
) {
}
