package org.lms.assignmentview.domain.user;

import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    @NonNull
    List<UserDetails> findAll();

    @NonNull
    Optional<UserDetails> findByUser(@NonNull final User user);

    @NonNull
    UserDetails save(@NonNull final UserDetails userDetails);
}
