package org.lms.assignmentview.infrastructure;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.user.User;
import org.lms.assignmentview.domain.user.UserDetails;
import org.lms.assignmentview.domain.user.UserRepository;
import org.lms.assignmentview.infrastructure.jpa.entity.DiscussionUserEntity;
import org.lms.assignmentview.infrastructure.jpa.repository.JpaDiscussionUserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {

    @NonNull
    private final JpaDiscussionUserRepository jpaDiscussionUserRepository;

    @Override
    public @NonNull List<UserDetails> findAll() {
        return jpaDiscussionUserRepository.findAll().stream()
                .map(DiscussionUserEntity::toDomain)
                .toList();
    }

    @Override
    public @NonNull List<UserDetails> findAllByCourseId(@NonNull CourseId courseId) {
        return jpaDiscussionUserRepository.findAllByClassId(courseId.id()).stream()
                .map(DiscussionUserEntity::toDomain)
                .toList();
    }

    @Override
    public @NonNull Optional<UserDetails> findByUser(@NonNull User user) {
        return jpaDiscussionUserRepository.findByIdAndClassId(user.userId().id(), user.classId().id())
                .map(DiscussionUserEntity::toDomain);
    }

    @Override
    public @NonNull UserDetails save(@NonNull UserDetails userDetails) {
        final DiscussionUserEntity userEntity = DiscussionUserEntity.from(userDetails);
        return jpaDiscussionUserRepository.save(userEntity).toDomain();
    }
}
