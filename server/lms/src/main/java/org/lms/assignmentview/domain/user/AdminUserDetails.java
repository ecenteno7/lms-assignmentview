package org.lms.assignmentview.domain.user;

import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import org.lms.assignmentview.domain.user.commands.CreateUserCommand;

@Getter
@SuperBuilder
public class AdminUserDetails extends UserDetails {

    @NonNull
    private final String username;

    @NonNull
    private final String password;

    public static @NonNull AdminUserDetails from(@NonNull final CreateUserCommand createUserCommand) {
        return AdminUserDetails.builder()
                .user(User.generateUser(createUserCommand.classId()))
                .username(createUserCommand.username())
                .password(createUserCommand.password())
                .firstName(createUserCommand.firstName())
                .lastName(createUserCommand.lastName())
                .role(createUserCommand.role())
                .build();
    }

}
