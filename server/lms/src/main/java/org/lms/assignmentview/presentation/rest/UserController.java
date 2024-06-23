package org.lms.assignmentview.presentation.rest;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.application.UserApplicationService;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.user.*;
import org.lms.assignmentview.domain.user.commands.CreateUserCommand;
import org.lms.assignmentview.domain.user.commands.UserLoginCommand;
import org.lms.assignmentview.presentation.rest.dto.user.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {

    @NonNull
    private final UserApplicationService userApplicationService;

    @PostMapping("/api/admin/courses/{course-id}/users")
    public @NonNull AdminUserResponseDto createUser(@NonNull @PathVariable("course-id") final String courseId,
                                                    @NonNull @RequestBody UserRequestDto userRequestDto) {
        final List<CreateUserCommand> createUserCommands = userRequestDto.users().stream()
                .map(AdminUserDto::toCreateUserCommand)
                .toList();
        final List<AdminUserDetails> userDetails = userApplicationService.createUser(createUserCommands);
        return AdminUserResponseDto.from(userDetails);
    }

    @GetMapping("/api/admin/courses/{course-id}/users")
    public @NonNull AdminUserResponseDto getAllUsersForCourse(@NonNull @PathVariable("course-id") final String courseId) {
        final List<AdminUserDetails> users = userApplicationService.getUsersForCourse(new CourseId(courseId));
        return AdminUserResponseDto.from(users);
    }

    @GetMapping("/api/admin/courses/{course-id}/users/{user-id}")
    public @NonNull UserDetailsDto getUserById(@NonNull @PathVariable("course-id") final String courseId,
                                               @NonNull @PathVariable("user-id") final String userId) {
        final User user = new User(new UserId(userId), new CourseId(courseId));
        final UserDetails userDetails = userApplicationService.findByUser(user);
        return UserDetailsDto.from(userDetails);
    }

    @PostMapping("/api/login")
    public @NonNull UserLoginResponseDto getLoginDetails(
            @NonNull @RequestBody final UserLoginRequestDto userLoginRequestDto
    ) {
        final UserLoginCommand userLoginCommand = userLoginRequestDto.toUserLoginCommand();
        final UserLoginView userLoginView = userApplicationService.login(userLoginCommand);
        return UserLoginResponseDto.from(userLoginView);
    }

}
