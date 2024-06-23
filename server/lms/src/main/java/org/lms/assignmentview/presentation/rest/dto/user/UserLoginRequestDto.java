package org.lms.assignmentview.presentation.rest.dto.user;

import lombok.NonNull;
import org.lms.assignmentview.domain.user.commands.UserLoginCommand;

public record UserLoginRequestDto(
        @NonNull String username,
        @NonNull String password
) {

    public @NonNull UserLoginCommand toUserLoginCommand() {
        return new UserLoginCommand(username, password);
    }

}
