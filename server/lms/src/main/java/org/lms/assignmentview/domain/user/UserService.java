package org.lms.assignmentview.domain.user;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.domain.user.commands.CreateUserCommand;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    @NonNull
    private final UserRepository userRepository;

    public @NonNull UserDetails createUser(@NonNull CreateUserCommand createUserCommand) {
        final UserDetails userDetails = UserDetails.from(createUserCommand);
        return userRepository.save(userDetails);
    }

}
