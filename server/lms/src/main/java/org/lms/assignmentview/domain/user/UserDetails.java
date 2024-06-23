package org.lms.assignmentview.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@Getter
@SuperBuilder(toBuilder = true)
public class UserDetails {

    @NonNull
    private final User user;

    @NonNull
    private final String firstName;

    @NonNull
    private final String lastName;

    @NonNull
    private final Role role;

//    public static @NonNull UserDetails from(@NonNull final CreateUserCommand createUserCommand) {
//        return UserDetails.builder()
//                .user(User.generateUser(createUserCommand.classId()))
//                .username(createUserCommand.username())
//                .password(createUserCommand.password())
//                .firstName(createUserCommand.firstName())
//                .lastName(createUserCommand.lastName())
//                .role(createUserCommand.role())
//                .build();
//    }

    public boolean isStaff() {
        return role.isStaff();
    }

}
