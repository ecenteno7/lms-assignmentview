package org.lms.assignmentview.infrastructure.jpa.repository;

import org.lms.assignmentview.infrastructure.jpa.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaTagRepository extends JpaRepository<TagEntity, UUID> {

    List<TagEntity> findAllByClassId(String classId);

}
