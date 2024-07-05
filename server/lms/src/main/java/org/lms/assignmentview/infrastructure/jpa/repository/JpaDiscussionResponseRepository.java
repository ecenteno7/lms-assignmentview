package org.lms.assignmentview.infrastructure.jpa.repository;

import org.lms.assignmentview.infrastructure.jpa.entity.DiscussionResponseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaDiscussionResponseRepository extends JpaRepository<DiscussionResponseEntity, UUID> {

}
