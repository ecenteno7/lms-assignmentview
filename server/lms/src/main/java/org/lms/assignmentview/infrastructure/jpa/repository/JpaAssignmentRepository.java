package org.lms.assignmentview.infrastructure.jpa.repository;

import org.lms.assignmentview.infrastructure.jpa.entity.AssignmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaAssignmentRepository extends JpaRepository<AssignmentEntity, UUID> {

    List<AssignmentEntity> findAllByClassId(String classId);

}
