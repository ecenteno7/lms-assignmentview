package org.lms.assignmentview.infrastructure;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.domain.course.ClassRepository;
import org.lms.assignmentview.domain.course.Course;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.infrastructure.jpa.entity.ClassEntity;
import org.lms.assignmentview.infrastructure.jpa.repository.JpaClassRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class ClassRepositoryImpl implements ClassRepository {

    @NonNull
    private final JpaClassRepository jpaClassRepository;

    @Override
    public @NonNull List<Course> findAll() {
        return jpaClassRepository.findAll().stream()
                .map(ClassEntity::toDomain)
                .toList();
    }

    @Override
    public @NonNull Optional<Course> findById(@NonNull CourseId id) {
        return jpaClassRepository.findById(id.id()).map(ClassEntity::toDomain);
    }

    @Override
    public @NonNull Course save(@NonNull Course course) {
        final ClassEntity classEntity = ClassEntity.from(course);
        return jpaClassRepository.save(classEntity).toDomain();
    }

    @Override
    public @NonNull List<Course> saveAll(@NonNull List<Course> courses) {
        final List<ClassEntity> classEntities = courses.stream()
                .map(ClassEntity::from)
                .toList();
        return jpaClassRepository.saveAll(classEntities).stream()
                .map(ClassEntity::toDomain)
                .toList();
    }
}
