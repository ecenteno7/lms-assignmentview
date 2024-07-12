package org.lms.assignmentview.infrastructure;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.domain.assignment.Assignment;
import org.lms.assignmentview.domain.assignment.AssignmentId;
import org.lms.assignmentview.domain.assignment.AssignmentRepository;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.infrastructure.jpa.entity.AssignmentEntity;
import org.lms.assignmentview.infrastructure.jpa.repository.JpaAssignmentRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Repository
public class AssignmentRepositoryImpl implements AssignmentRepository {

    @NonNull
    private final JpaAssignmentRepository jpaAssignmentRepository;

    @Override
    public @NonNull List<Assignment> findAllByCourseId(@NonNull final CourseId courseId) {
        return jpaAssignmentRepository.findAllByClassId(courseId.id()).stream()
                .map(AssignmentEntity::toDomain)
                .toList();
    }

    @Override
    public @NonNull Optional<Assignment> findById(@NonNull AssignmentId assignmentId) {
        return jpaAssignmentRepository.findById(UUID.fromString(assignmentId.id())).map(AssignmentEntity::toDomain);
    }

    @Override
    public @NonNull List<Assignment> saveAll(@NonNull final List<Assignment> assignments) {
        final List<AssignmentEntity> assignmentEntities = assignments.stream()
                .map(AssignmentEntity::from)
                .toList();
        return jpaAssignmentRepository.saveAll(assignmentEntities).stream()
                .map(AssignmentEntity::toDomain)
                .toList();
    }
}
