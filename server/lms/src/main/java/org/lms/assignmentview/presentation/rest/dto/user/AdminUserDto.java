package org.lms.assignmentview.presentation.rest.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.NonNull;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.user.AdminUserDetails;
import org.lms.assignmentview.domain.user.Role;
import org.lms.assignmentview.domain.user.commands.CreateUserCommand;

@Builder(toBuilder = true)
public record AdminUserDto(
        @JsonProperty("userID")
        @Nullable String userId,

        @JsonProperty("classID")
        @NonNull String classId,

        @NonNull String username,

        @NonNull String password,

        @NonNull String firstName,

        @NonNull String lastName,

        @NonNull String role
) {

    public static @NonNull AdminUserDto from(@NonNull final AdminUserDetails userDetails) {
        return AdminUserDto.builder()
                .userId(userDetails.getUser().userId().id())
                .classId(userDetails.getUser().classId().id())
                .username(userDetails.getUsername())
                .password(userDetails.getPassword())
                .firstName(userDetails.getFirstName())
                .lastName(userDetails.getLastName())
                .role(userDetails.getRole().name())
                .build();
    }

    public @NonNull CreateUserCommand toCreateUserCommand() {
        return CreateUserCommand.builder()
                .username(username)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .classId(new CourseId(classId))
                .role(Role.valueOf(role))
                .build();
    }

}
