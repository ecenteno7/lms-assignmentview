package org.lms.assignmentview.infrastructure;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.domain.discussion.DiscussionPost;
import org.lms.assignmentview.domain.discussion.DiscussionPostId;
import org.lms.assignmentview.domain.discussion.DiscussionPostRepository;
import org.lms.assignmentview.infrastructure.jpa.entity.DiscussionPostEntity;
import org.lms.assignmentview.infrastructure.jpa.repository.JpaDiscussionPostRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@AllArgsConstructor
@Repository
public class DiscussionPostRepositoryImpl implements DiscussionPostRepository {

    @NonNull
    private final JpaDiscussionPostRepository jpaDiscussionPostRepository;

    @Override
    public @NonNull Optional<DiscussionPost> findById(@NonNull final DiscussionPostId discussionPostId) {
        return jpaDiscussionPostRepository.findById(discussionPostId.id()).map(DiscussionPostEntity::toDomain);
    }

    @Override
    public @NonNull DiscussionPost save(@NonNull final DiscussionPost discussionPost) {
        final DiscussionPostEntity discussionPostEntity = DiscussionPostEntity.from(discussionPost);
        return jpaDiscussionPostRepository.save(discussionPostEntity).toDomain();
    }

}
