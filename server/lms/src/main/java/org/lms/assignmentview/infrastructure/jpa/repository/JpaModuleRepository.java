package org.lms.assignmentview.infrastructure.jpa.repository;

import org.lms.assignmentview.infrastructure.jpa.entity.ModuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaModuleRepository extends JpaRepository<ModuleEntity, UUID> {
}
