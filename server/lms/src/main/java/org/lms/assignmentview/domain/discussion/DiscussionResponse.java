package org.lms.assignmentview.domain.discussion;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import org.lms.assignmentview.domain.discussion.command.CreateDiscussionResponseCommand;
import org.lms.assignmentview.domain.discussion.command.UpdateDiscussionResponseCommand;
import org.lms.assignmentview.domain.user.User;
import org.springframework.lang.Nullable;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Value
@Builder(toBuilder = true)
public class DiscussionResponse {

    @NonNull
    private final DiscussionResponseId id;

    @NonNull
    private final DiscussionPostId parentPostId;

    @Nullable
    private final DiscussionResponseId parentResponseId;

    @NonNull
    private final User author;

    @NonNull
    private final OffsetDateTime createdOn;

    @Nullable
    private OffsetDateTime updatedOn;

    @NonNull
    private String content;

    private int voteCount;

    private boolean accepted;

    @NonNull
    @Builder.Default
    private final List<DiscussionResponse> responses = List.of();

    public @NonNull Optional<OffsetDateTime> getUpdatedOn() {
        return Optional.ofNullable(updatedOn);
    }

    public @NonNull Optional<DiscussionResponseId> getParentResponseId() {
        return Optional.ofNullable(parentResponseId);
    }

    public static @NonNull DiscussionResponse createDiscussionResponse(
            @NonNull final CreateDiscussionResponseCommand command
    ) {
        return DiscussionResponse.builder()
                .id(DiscussionResponseId.createId())
                .parentPostId(command.parentPostId())
                .parentResponseId(command.parentResponseId())
                .author(command.author())
                .createdOn(OffsetDateTime.now())
                .content(command.content())
                .voteCount(0)
                .build();
    }

    public @NonNull DiscussionResponse updateDiscussionResponse(
            @NonNull final UpdateDiscussionResponseCommand command,
            @NonNull final DiscussionPost discussionPost
    ) {
        if (command.getAccepted().isPresent() && discussionPost.hasAcceptedResponse()) {
            if (command.getAccepted().orElse(this.isAccepted())) {
                throw new IllegalArgumentException(
                        "Cannot accept a discussion response when another response is already accepted.");
            }
        }
        return this.toBuilder()
                .accepted(command.getAccepted().orElse(this.isAccepted()))
                .content(command.getContent().orElse(this.getContent()))
                .updatedOn(OffsetDateTime.now())
                .build();
    }

    public boolean hasAcceptedResponse() {
        return this.accepted || responses.stream().anyMatch(DiscussionResponse::hasAcceptedResponse);
    }

    @NonNull Stream<User> getInvolvedUsers() {
        final Stream<User> responseUsers = responses.stream()
                .flatMap(DiscussionResponse::getInvolvedUsers);
        return Stream.concat(Stream.of(author), responseUsers)
                .distinct();
    }

}
