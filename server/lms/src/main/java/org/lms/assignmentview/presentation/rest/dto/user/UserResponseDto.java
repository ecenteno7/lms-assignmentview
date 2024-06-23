package org.lms.assignmentview.presentation.rest.dto.user;

import lombok.NonNull;
import org.lms.assignmentview.domain.user.UserDetails;

import java.util.List;

public record UserResponseDto(
        @NonNull List<UserDto> users
) {

    public static @NonNull UserResponseDto from(@NonNull final List<UserDetails> userDetails) {
        final List<UserDto> userDtos = userDetails.stream()
                .map(UserDto::from)
                .toList();
        return new UserResponseDto(userDtos);
    }

}
