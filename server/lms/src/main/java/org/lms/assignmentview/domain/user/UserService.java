package org.lms.assignmentview.domain.user;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.user.commands.CreateUserCommand;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    @NonNull
    private final UserRepository userRepository;

    public @NonNull List<UserDetails> createUsers(@NonNull List<CreateUserCommand> createUserCommands) {
        final List<UserDetails> userDetails = createUserCommands.stream()
                .map(UserDetails::from)
                .toList();
        return userRepository.saveAll(userDetails);
    }

    public @NonNull List<UserDetails> getUsersForCourse(@NonNull final CourseId courseId) {
        return userRepository.findAllByCourseId(courseId);
    }

    public @NonNull UserDetails findByUser(@NonNull final User user) {
        return userRepository.findByUser(user).orElseThrow();
    }

}
