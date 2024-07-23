package org.lms.assignmentview.infrastructure.jpa.repository;

import org.lms.assignmentview.infrastructure.jpa.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface JpaTagRepository extends JpaRepository<TagEntity, UUID> {

    List<TagEntity> findAllByClassId(String classId);

    @Query(value = """    
            WITH RECURSIVE x AS (
                SELECT * FROM tag WHERE id = :tagId
                UNION
                SELECT t.* FROM tag t INNER JOIN x x1 ON t.parent_tag_id = x1.id
            ) SELECT * FROM x
            """, nativeQuery = true)
    List<TagEntity> findTagWithChildren(@Param("tagId") UUID tagId);

}
