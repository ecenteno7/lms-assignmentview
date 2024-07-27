package org.lms.assignmentview.application;

import lombok.NonNull;
import org.lms.assignmentview.domain.chatter.Message;
import org.lms.assignmentview.domain.user.UserDetails;

public record MessageView(
        @NonNull Message message,
        @NonNull UserDetails authorDetails
) {
}
