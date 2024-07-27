package org.lms.assignmentview.infrastructure.jpa.repository;

import org.lms.assignmentview.infrastructure.jpa.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaMessageRepository extends JpaRepository<MessageEntity, UUID> {

    List<MessageEntity> findAllByClassIdOrderByCreatedOnAsc(String classId);

}
