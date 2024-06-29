package org.lms.assignmentview.infrastructure.jpa.repository;

import org.lms.assignmentview.infrastructure.jpa.entity.DiscussionUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaDiscussionUserRepository extends JpaRepository<DiscussionUserEntity, String> {

    Optional<DiscussionUserEntity> findByIdAndClassId(String id, String classId);

    Optional<DiscussionUserEntity> findByUsernameAndPassword(String username, String password);

    List<DiscussionUserEntity> findAllByClassId(String classId);

    List<DiscussionUserEntity> findAllByIdInAndClassId(List<String> ids, String classId);

}
