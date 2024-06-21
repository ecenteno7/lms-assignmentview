package org.lms.assignmentview.infrastructure.jpa.repository;

import org.lms.assignmentview.infrastructure.jpa.entity.DiscussionPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDiscussionPostRepository extends JpaRepository<DiscussionPostEntity, String> {
}
