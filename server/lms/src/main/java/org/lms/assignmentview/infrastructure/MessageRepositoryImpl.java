package org.lms.assignmentview.infrastructure;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.domain.chatter.Message;
import org.lms.assignmentview.domain.chatter.MessageRepository;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.infrastructure.jpa.entity.MessageEntity;
import org.lms.assignmentview.infrastructure.jpa.repository.JpaMessageRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class MessageRepositoryImpl implements MessageRepository {

    @NonNull
    private final JpaMessageRepository jpaMessageRepository;

    @Override
    public @NonNull Message save(@NonNull final Message message) {
        final MessageEntity messageEntity = jpaMessageRepository.save(MessageEntity.from(message));
        return messageEntity.toDomain();
    }

    @Override
    public @NonNull List<Message> getMessagesByCourseId(@NonNull final CourseId courseId) {
        return jpaMessageRepository.findAllByClassIdOrderByCreatedOnAsc(courseId.id()).stream()
                .map(MessageEntity::toDomain)
                .toList();
    }
}
