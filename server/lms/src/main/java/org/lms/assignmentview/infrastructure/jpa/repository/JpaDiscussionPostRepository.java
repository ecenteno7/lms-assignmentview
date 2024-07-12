package org.lms.assignmentview.infrastructure.jpa.repository;

import org.lms.assignmentview.infrastructure.jpa.entity.DiscussionPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface JpaDiscussionPostRepository extends JpaRepository<DiscussionPostEntity, UUID> {

    List<DiscussionPostEntity> findAllByClassId(String classId);

    @Query(value = "SELECT * FROM discussion_post JOIN post_tags ON post_tags.discussion_post_id = discussion_post.id AND post_tags.tag_id = :tagId", nativeQuery = true)
    List<DiscussionPostEntity> findAllPostsWithTag(@Param("tagId") UUID tagId);

}
