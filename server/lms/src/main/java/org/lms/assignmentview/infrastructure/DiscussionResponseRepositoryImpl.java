package org.lms.assignmentview.infrastructure;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.domain.discussion.DiscussionResponse;
import org.lms.assignmentview.domain.discussion.DiscussionResponseRepository;
import org.lms.assignmentview.infrastructure.jpa.entity.DiscussionResponseEntity;
import org.lms.assignmentview.infrastructure.jpa.repository.JpaDiscussionResponseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository
public class DiscussionResponseRepositoryImpl implements DiscussionResponseRepository {

    @NonNull
    private final JpaDiscussionResponseRepository jpaDiscussionResponseRepository;

    @Override
    public @NonNull List<DiscussionResponse> saveAllResponses(@NonNull List<DiscussionResponse> discussionResponses) {
        final List<DiscussionResponseEntity> discussionResponseEntities = discussionResponses.stream()
                .map(DiscussionResponseEntity::from)
                .toList();
        return jpaDiscussionResponseRepository.saveAll(discussionResponseEntities).stream()
                .map(DiscussionResponseEntity::toDomain)
                .toList();
    }

}
