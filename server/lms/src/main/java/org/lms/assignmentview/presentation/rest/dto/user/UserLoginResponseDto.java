package org.lms.assignmentview.presentation.rest.dto.user;

import lombok.NonNull;
import org.lms.assignmentview.domain.user.UserLoginView;
import org.lms.assignmentview.presentation.rest.dto.course.CourseDto;

public record UserLoginResponseDto(
        @NonNull UserDetailsDto userDetails,
        @NonNull CourseDto course
) {

    public static @NonNull UserLoginResponseDto from(@NonNull final UserLoginView userLoginView) {
        return new UserLoginResponseDto(
                UserDetailsDto.from(userLoginView.userDetails()),
                CourseDto.from(userLoginView.course())
        );
    }

}
