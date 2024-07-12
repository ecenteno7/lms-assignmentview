package org.lms.assignmentview.domain.discussion;

import lombok.NonNull;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.tag.TagId;

import java.util.List;
import java.util.Optional;

public interface DiscussionPostRepository {

    @NonNull
    Optional<DiscussionPost> findById(@NonNull final DiscussionPostId discussionPostId);

    @NonNull List<DiscussionPost> saveAll(@NonNull final List<DiscussionPost> discussionPosts);

    @NonNull List<DiscussionPost> findAllByCourseId(@NonNull final CourseId courseId);

    @NonNull List<DiscussionPost> findAllByTagId(@NonNull final TagId tagId);

}
