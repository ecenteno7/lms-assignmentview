package org.lms.assignmentview.presentation.rest.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.NonNull;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.user.Role;
import org.lms.assignmentview.domain.user.UserDetails;
import org.lms.assignmentview.domain.user.commands.CreateUserCommand;

@Builder(toBuilder = true)
public record UserDto(
        @JsonProperty("userID")
        @Nullable String userId,

        @JsonProperty("classID")
        @NonNull String classId,

        @NonNull String username,

        @NonNull String firstName,

        @NonNull String lastName,

        @NonNull String role
) {

    public static @NonNull UserDto from(@NonNull final UserDetails userDetails) {
        return UserDto.builder()
                .userId(userDetails.getUser().userId().id())
                .classId(userDetails.getUser().classId().id())
                .username(userDetails.getUsername())
                .firstName(userDetails.getFirstName())
                .lastName(userDetails.getLastName())
                .role(userDetails.getRole().name())
                .build();
    }

    public @NonNull CreateUserCommand toCreateUserCommand() {
        return CreateUserCommand.builder()
                .username(username)
                .firstName(firstName)
                .lastName(lastName)
                .classId(new CourseId(classId))
                .role(Role.valueOf(role))
                .build();
    }

}
