package org.lms.assignmentview.application;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.user.User;
import org.lms.assignmentview.domain.user.UserDetails;
import org.lms.assignmentview.domain.user.UserService;
import org.lms.assignmentview.domain.user.commands.CreateUserCommand;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UserApplicationService {

    @NonNull
    private final UserService userService;

    @Transactional
    public @NonNull List<UserDetails> createUser(@NonNull final List<CreateUserCommand> createUserCommands) {
        return userService.createUsers(createUserCommands);
    }

    @Transactional(readOnly = true)
    public @NonNull List<UserDetails> getUsersForCourse(@NonNull final CourseId courseId) {
        return userService.getUsersForCourse(courseId);
    }

    @Transactional(readOnly = true)
    public @NonNull UserDetails findByUser(@NonNull final User user) {
        return userService.findByUser(user);
    }

}
