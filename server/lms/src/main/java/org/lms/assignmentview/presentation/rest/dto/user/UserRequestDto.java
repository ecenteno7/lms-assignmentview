package org.lms.assignmentview.presentation.rest.dto.user;

import lombok.NonNull;

import java.util.List;

public record UserRequestDto(
        @NonNull List<AdminUserDto> users
) {
}
