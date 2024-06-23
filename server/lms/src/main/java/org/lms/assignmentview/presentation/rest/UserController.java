package org.lms.assignmentview.presentation.rest;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.application.UserApplicationService;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.user.User;
import org.lms.assignmentview.domain.user.UserDetails;
import org.lms.assignmentview.domain.user.UserId;
import org.lms.assignmentview.domain.user.commands.CreateUserCommand;
import org.lms.assignmentview.presentation.rest.dto.user.UserRequestDto;
import org.lms.assignmentview.presentation.rest.dto.user.UserResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {

    @NonNull
    private final UserApplicationService userApplicationService;

    @PostMapping("/api/admin/courses/{course-id}/users")
    public @NonNull UserResponseDto createUser(@NonNull @PathVariable("course-id") final String courseId,
                                               @NonNull @RequestBody UserRequestDto userRequestDto) {
        if (userRequestDto.users().size() != 1) {
            throw new IllegalStateException("Application only supports adding a single user at a time");
        }
        final CreateUserCommand createUserCommand = userRequestDto.users().get(0).toCreateUserCommand();
        final UserDetails userDetails = userApplicationService.createUser(createUserCommand);
        return UserResponseDto.from(List.of(userDetails));
    }

    @GetMapping("/api/admin/courses/{course-id}/users")
    public @NonNull UserResponseDto getAllUsersForCourse(@NonNull @PathVariable("course-id") final String courseId) {
        final List<UserDetails> users = userApplicationService.getUsersForCourse(new CourseId(courseId));
        return UserResponseDto.from(users);
    }

    @GetMapping("/api/admin/courses/{course-id}/users/{user-id}")
    public @NonNull UserResponseDto getUserById(@NonNull @PathVariable("course-id") final String courseId,
                                                @NonNull @PathVariable("user-id") final String userId) {
        final User user = new User(new UserId(userId), new CourseId(courseId));
        final UserDetails userDetails = userApplicationService.findByUser(user);
        return UserResponseDto.from(List.of(userDetails));
    }

}
