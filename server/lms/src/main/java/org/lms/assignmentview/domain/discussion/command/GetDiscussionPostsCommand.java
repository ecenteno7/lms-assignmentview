package org.lms.assignmentview.domain.discussion.command;

import lombok.NonNull;
import org.lms.assignmentview.domain.course.CourseId;

public record GetDiscussionPostsCommand(
        @NonNull CourseId courseId
) {
}
