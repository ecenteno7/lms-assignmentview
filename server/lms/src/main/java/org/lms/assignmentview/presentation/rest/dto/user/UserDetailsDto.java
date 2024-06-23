package org.lms.assignmentview.presentation.rest.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.NonNull;
import org.lms.assignmentview.domain.user.UserDetails;

@Builder
public record UserDetailsDto(
        @JsonProperty("userID")
        @NonNull String userId,

        @JsonProperty("classID")
        @NonNull String classId,

        @NonNull String firstName,

        @NonNull String lastName,

        @NonNull String role
) {

    public static @NonNull UserDetailsDto from(@NonNull final UserDetails userDetails) {
        return UserDetailsDto.builder()
                .userId(userDetails.getUser().userId().id())
                .classId(userDetails.getUser().classId().id())
                .firstName(userDetails.getFirstName())
                .lastName(userDetails.getLastName())
                .role(userDetails.getRole().name())
                .build();
    }

}
