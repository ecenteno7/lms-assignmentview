package org.lms.assignmentview.domain.discussion.command;

import org.lms.assignmentview.domain.discussion.DiscussionSelection;
import org.lms.assignmentview.domain.tag.TagId;
import org.lms.assignmentview.domain.user.User;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public record CreateDiscussionPostCommand(
        @NonNull User author,
        @NonNull String title,
        @NonNull String content,
        @NonNull List<TagId> tagIds,
        @Nullable DiscussionSelection discussionSelection
) {

    public @NonNull Optional<DiscussionSelection> getDiscussionSelection() {
        return Optional.ofNullable(discussionSelection);
    }

    public @NonNull Stream<TagId> getReferencedTagIds() {
        final Stream<TagId> discussionSelectionTagIdStream = getDiscussionSelection().stream()
                .map(DiscussionSelection::tagId);
        final Stream<TagId> tagIdStream = tagIds.stream();
        return Stream.concat(discussionSelectionTagIdStream, tagIdStream)
                .distinct();
    }

}
