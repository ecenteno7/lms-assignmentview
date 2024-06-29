package org.lms.assignmentview.domain.user;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.user.commands.CreateUserCommand;
import org.lms.assignmentview.domain.user.commands.UserLoginCommand;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {

    @NonNull
    private final UserRepository userRepository;

    public @NonNull List<AdminUserDetails> createUsers(@NonNull List<CreateUserCommand> createUserCommands) {
        final List<AdminUserDetails> userDetails = createUserCommands.stream()
                .map(AdminUserDetails::from)
                .toList();
        return userRepository.saveAll(userDetails);
    }

    public @NonNull List<AdminUserDetails> getUsersForCourse(@NonNull final CourseId courseId) {
        return userRepository.findAllByCourseId(courseId);
    }

    public @NonNull UserDetails findByUser(@NonNull final User user) {
        return userRepository.findByUser(user).orElseThrow();
    }

    public @NonNull Map<User, UserDetails> findByUsers(@NonNull final Set<User> users) {
        return userRepository.findByUsers(users);
    }
    
    public @NonNull UserDetails login(@NonNull final UserLoginCommand userLoginCommand) {
        return userRepository.login(userLoginCommand).orElseThrow();
    }

}
