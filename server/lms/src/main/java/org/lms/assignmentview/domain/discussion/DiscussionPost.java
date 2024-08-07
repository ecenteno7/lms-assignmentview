package org.lms.assignmentview.domain.discussion;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import org.lms.assignmentview.domain.discussion.command.CreateDiscussionPostCommand;
import org.lms.assignmentview.domain.tag.Tag;
import org.lms.assignmentview.domain.tag.TagId;
import org.lms.assignmentview.domain.user.User;
import org.springframework.lang.Nullable;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@Value
@Builder(toBuilder = true)
public class DiscussionPost {

    @NonNull
    private final DiscussionPostId id;

    @NonNull
    private final User author;

    @NonNull
    private final OffsetDateTime createdOn;

    @Nullable
    private OffsetDateTime updatedOn;

    @NonNull
    private String title;

    @NonNull
    private String content;

    private int voteCount;

    @NonNull
    @Builder.Default
    private final List<DiscussionResponse> responses = List.of();

    @NonNull
    @Builder.Default
    private final List<Tag> tags = List.of();

    @Nullable
    private final DiscussionSelection discussionSelection;

    public @NonNull Optional<OffsetDateTime> getUpdatedOn() {
        return Optional.ofNullable(updatedOn);
    }

    public @NonNull Optional<DiscussionSelection> getDiscussionSelection() {
        return Optional.ofNullable(discussionSelection);
    }

    static @NonNull DiscussionPost createDiscussionPost(@NonNull final CreateDiscussionPostCommand command,
                                                        @NonNull final Map<TagId, Tag> tagsById) {
        return DiscussionPost.builder()
                .id(DiscussionPostId.createId())
                .author(command.author())
                .createdOn(OffsetDateTime.now())
                .title(command.title())
                .content(command.content())
                .voteCount(0)
                .tags(command.getReferencedTagIds()
                        .map(tagsById::get)
                        .toList())
                .discussionSelection(command.discussionSelection())
                .build();
    }

    public boolean hasAcceptedResponse() {
        return responses.stream()
                .anyMatch(DiscussionResponse::hasAcceptedResponse);
    }

    public @NonNull DiscussionPost withOnlyAcceptedResponses() {
        return this.toBuilder()
                .responses(responses.stream()
                        .filter(DiscussionResponse::hasAcceptedResponse)
                        .toList())
                .build();
    }

    public @NonNull Stream<User> getInvolvedUsers() {
        final Stream<User> responseUsers = responses.stream()
                .flatMap(DiscussionResponse::getInvolvedUsers);
        return Stream.concat(Stream.of(author), responseUsers)
                .distinct();
    }

}
