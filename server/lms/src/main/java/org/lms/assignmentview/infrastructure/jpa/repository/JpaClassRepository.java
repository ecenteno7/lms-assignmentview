package org.lms.assignmentview.infrastructure.jpa.repository;

import org.lms.assignmentview.infrastructure.jpa.entity.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaClassRepository extends JpaRepository<ClassEntity, String> {
}
