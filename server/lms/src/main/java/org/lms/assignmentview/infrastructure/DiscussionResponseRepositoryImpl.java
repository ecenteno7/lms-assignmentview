package org.lms.assignmentview.infrastructure;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.domain.discussion.DiscussionResponse;
import org.lms.assignmentview.domain.discussion.DiscussionResponseId;
import org.lms.assignmentview.domain.discussion.DiscussionResponseRepository;
import org.lms.assignmentview.infrastructure.jpa.entity.DiscussionResponseEntity;
import org.lms.assignmentview.infrastructure.jpa.repository.JpaDiscussionResponseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Repository
public class DiscussionResponseRepositoryImpl implements DiscussionResponseRepository {

    @NonNull
    private final JpaDiscussionResponseRepository jpaDiscussionResponseRepository;

    @Override
    public @NonNull DiscussionResponse save(@NonNull final DiscussionResponse discussionResponse) {
        final DiscussionResponseEntity discussionResponseEntity = DiscussionResponseEntity.from(discussionResponse);
        final DiscussionResponseEntity savedDiscussionResponseEntity =
                jpaDiscussionResponseRepository.save(discussionResponseEntity);
        return savedDiscussionResponseEntity.toDomain();
    }

    @Override
    public @NonNull List<DiscussionResponse> saveAllResponses(
            @NonNull final List<DiscussionResponse> discussionResponses
    ) {
        final List<DiscussionResponseEntity> discussionResponseEntities = discussionResponses.stream()
                .map(DiscussionResponseEntity::from)
                .toList();
        return jpaDiscussionResponseRepository.saveAll(discussionResponseEntities).stream()
                .map(DiscussionResponseEntity::toDomain)
                .toList();
    }

    @Override
    public @NonNull Optional<DiscussionResponse> getDiscussionResponseById(
            @NonNull final DiscussionResponseId discussionResponseId
    ) {
        return jpaDiscussionResponseRepository.findById(UUID.fromString(discussionResponseId.id()))
                .map(DiscussionResponseEntity::toDomain);
    }

}
