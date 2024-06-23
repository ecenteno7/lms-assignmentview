package org.lms.assignmentview.application;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.domain.course.Course;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.course.CourseService;
import org.lms.assignmentview.domain.user.*;
import org.lms.assignmentview.domain.user.commands.CreateUserCommand;
import org.lms.assignmentview.domain.user.commands.UserLoginCommand;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UserApplicationService {

    @NonNull
    private final UserService userService;

    @NonNull
    private final CourseService courseService;

    @Transactional
    public @NonNull List<AdminUserDetails> createUser(@NonNull final List<CreateUserCommand> createUserCommands) {
        return userService.createUsers(createUserCommands);
    }

    @Transactional(readOnly = true)
    public @NonNull List<AdminUserDetails> getUsersForCourse(@NonNull final CourseId courseId) {
        return userService.getUsersForCourse(courseId);
    }

    @Transactional(readOnly = true)
    public @NonNull UserDetails findByUser(@NonNull final User user) {
        return userService.findByUser(user);
    }

    @Transactional(readOnly = true)
    public @NonNull UserLoginView login(@NonNull final UserLoginCommand userLoginCommand) {
        final UserDetails userDetails = userService.login(userLoginCommand);
        final Course course = courseService.getCourseById(userDetails.getUser().classId());
        return new UserLoginView(userDetails, course);
    }

}
