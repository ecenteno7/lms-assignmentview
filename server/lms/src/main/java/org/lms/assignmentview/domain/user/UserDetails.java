package org.lms.assignmentview.domain.user;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class UserDetails {

    @NonNull
    private final User user;

    @NonNull
    private final String username;

    @NonNull
    private final String firstName;

    @NonNull
    private final String lastName;

    @NonNull
    private final Role role;

}
