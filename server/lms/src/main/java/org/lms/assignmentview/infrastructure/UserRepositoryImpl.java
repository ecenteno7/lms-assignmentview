package org.lms.assignmentview.infrastructure;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.user.*;
import org.lms.assignmentview.domain.user.commands.UserLoginCommand;
import org.lms.assignmentview.infrastructure.jpa.entity.DiscussionUserEntity;
import org.lms.assignmentview.infrastructure.jpa.repository.JpaDiscussionUserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

@AllArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {

    @NonNull
    private final JpaDiscussionUserRepository jpaDiscussionUserRepository;

    @Override
    public @NonNull List<AdminUserDetails> findAllByCourseId(@NonNull CourseId courseId) {
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
    public @NonNull Map<User, UserDetails> findByUsers(@NonNull Set<User> users) {
        final CourseId courseId = users.stream().map(User::classId).findFirst().orElseThrow();
        if (users.stream().anyMatch(user -> !user.classId().equals(courseId))) {
            throw new IllegalArgumentException("All users must be from the same class.");
        }
        final List<String> userIds = users.stream()
                .map(User::userId)
                .map(UserId::id)
                .toList();
        return jpaDiscussionUserRepository.findAllByIdInAndClassId(userIds, courseId.id()).stream()
                .map(DiscussionUserEntity::toDomain)
                .collect(toMap(UserDetails::getUser, Function.identity()));
    }

    @Override
    public @NonNull Optional<UserDetails> login(@NonNull UserLoginCommand command) {
        return jpaDiscussionUserRepository.findByUsernameAndPassword(command.username(), command.password())
                .map(DiscussionUserEntity::toDomain);
    }

    @Override
    public @NonNull List<AdminUserDetails> saveAll(@NonNull List<AdminUserDetails> userDetailsList) {
        final List<DiscussionUserEntity> userEntities = userDetailsList.stream()
                .map(DiscussionUserEntity::from)
                .toList();
        return jpaDiscussionUserRepository.saveAll(userEntities).stream()
                .map(DiscussionUserEntity::toDomain)
                .toList();
    }
}
