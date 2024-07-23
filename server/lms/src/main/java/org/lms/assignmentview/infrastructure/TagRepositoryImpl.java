package org.lms.assignmentview.infrastructure;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.tag.Tag;
import org.lms.assignmentview.domain.tag.TagId;
import org.lms.assignmentview.domain.tag.TagRepository;
import org.lms.assignmentview.infrastructure.jpa.entity.TagEntity;
import org.lms.assignmentview.infrastructure.jpa.repository.JpaTagRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class TagRepositoryImpl implements TagRepository {

    @NonNull
    private final JpaTagRepository jpaTagRepository;

    @Override
    public @NonNull Set<Tag> findAllByIds(@NonNull final Set<TagId> tagIds) {
        final Set<UUID> tagUUIDs = tagIds.stream()
                .map(tagId -> UUID.fromString(tagId.id()))
                .collect(Collectors.toSet());
        return jpaTagRepository.findAllById(tagUUIDs).stream()
                .map(TagEntity::toDomain)
                .collect(Collectors.toSet());
    }

    @Override
    public @NonNull List<Tag> getCourseTags(@NonNull final CourseId courseId) {
        return jpaTagRepository.findAllByClassId(courseId.id()).stream()
                .map(TagEntity::toDomain)
                .toList();
    }

    @Override
    public @NonNull List<Tag> saveAll(@NonNull final List<Tag> tags) {
        final List<TagEntity> tagEntities = tags.stream()
                .map(TagEntity::from)
                .toList();
        return jpaTagRepository.saveAll(tagEntities).stream()
                .map(TagEntity::toDomain)
                .toList();
    }

    @Override
    public List<Tag> findTagWithChildren(TagId tagId) {
        return jpaTagRepository.findTagWithChildren(UUID.fromString(tagId.id())).stream()
                .map(TagEntity::toDomain)
                .toList();
    }
}
