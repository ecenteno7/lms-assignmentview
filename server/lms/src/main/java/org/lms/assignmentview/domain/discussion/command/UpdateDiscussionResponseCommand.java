package org.lms.assignmentview.domain.discussion.command;

import lombok.Builder;
import lombok.NonNull;
import org.lms.assignmentview.domain.discussion.DiscussionResponseId;
import org.springframework.lang.Nullable;

import java.util.Optional;

@Builder
public record UpdateDiscussionResponseCommand(
        @NonNull DiscussionResponseId discussionResponseId,
        @Nullable String content,
        @Nullable Boolean accepted
) {

    public @NonNull Optional<String> getContent() {
        return Optional.ofNullable(content);
    }

    public @NonNull Optional<Boolean> getAccepted() {
        return Optional.ofNullable(accepted);
    }

}
