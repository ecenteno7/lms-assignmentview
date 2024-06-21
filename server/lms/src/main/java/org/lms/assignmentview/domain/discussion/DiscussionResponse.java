package org.lms.assignmentview.domain.discussion;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import org.lms.assignmentview.domain.user.User;
import org.springframework.lang.Nullable;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Value
@Builder(toBuilder = true)
public class DiscussionResponse {

    @NonNull
    private final DiscussionResponseId id;

    @NonNull
    private final User author;

    @NonNull
    private final OffsetDateTime createdOn;

    @Nullable
    private OffsetDateTime updatedOn;

    @NonNull
    private String content;

    private int voteCount;

    @NonNull
    @Builder.Default
    private final List<DiscussionResponse> responses = List.of();

    public @NonNull Optional<OffsetDateTime> getUpdatedOn() {
        return Optional.ofNullable(updatedOn);
    }

}
