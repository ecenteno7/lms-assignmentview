package org.lms.assignmentview.presentation.rest.dto.user;

import lombok.NonNull;
import org.lms.assignmentview.domain.user.AdminUserDetails;

import java.util.List;

public record AdminUserResponseDto(
        @NonNull List<AdminUserDto> users
) {

    public static @NonNull AdminUserResponseDto from(@NonNull final List<AdminUserDetails> userDetails) {
        final List<AdminUserDto> adminUserDtos = userDetails.stream()
                .map(AdminUserDto::from)
                .toList();
        return new AdminUserResponseDto(adminUserDtos);
    }

}
