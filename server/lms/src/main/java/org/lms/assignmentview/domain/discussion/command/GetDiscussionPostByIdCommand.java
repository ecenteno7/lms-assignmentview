package org.lms.assignmentview.domain.discussion.command;

import lombok.NonNull;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.discussion.DiscussionPostId;

public record GetDiscussionPostByIdCommand(
        @NonNull CourseId courseId,
        @NonNull DiscussionPostId discussionPostId
) {
}
