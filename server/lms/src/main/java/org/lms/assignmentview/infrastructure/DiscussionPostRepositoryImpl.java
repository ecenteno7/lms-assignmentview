package org.lms.assignmentview.infrastructure;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.discussion.DiscussionPost;
import org.lms.assignmentview.domain.discussion.DiscussionPostId;
import org.lms.assignmentview.domain.discussion.DiscussionPostRepository;
import org.lms.assignmentview.infrastructure.jpa.entity.DiscussionPostEntity;
import org.lms.assignmentview.infrastructure.jpa.repository.JpaDiscussionPostRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Repository
public class DiscussionPostRepositoryImpl implements DiscussionPostRepository {

    @NonNull
    private final JpaDiscussionPostRepository jpaDiscussionPostRepository;

    @Override
    public @NonNull Optional<DiscussionPost> findById(@NonNull final DiscussionPostId discussionPostId) {
        return jpaDiscussionPostRepository.findById(UUID.fromString(discussionPostId.id())).map(DiscussionPostEntity::toDomain);
    }

    @Override
    public @NonNull List<DiscussionPost> saveAll(@NonNull List<DiscussionPost> discussionPosts) {
        final List<DiscussionPostEntity> discussionPostEntities = discussionPosts.stream()
                .map(DiscussionPostEntity::from)
                .toList();
        return jpaDiscussionPostRepository.saveAll(discussionPostEntities).stream()
                .map(DiscussionPostEntity::toDomain)
                .toList();
    }

    @Override
    public @NonNull List<DiscussionPost> findAllByCourseId(@NonNull CourseId courseId) {
        return jpaDiscussionPostRepository.findAllByClassId(courseId.id()).stream()
                .map(DiscussionPostEntity::toDomain)
                .toList();
    }

}
