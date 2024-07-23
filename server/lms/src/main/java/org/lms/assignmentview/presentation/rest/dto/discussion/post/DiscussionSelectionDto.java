package org.lms.assignmentview.presentation.rest.dto.discussion.post;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;
import org.lms.assignmentview.domain.discussion.DiscussionSelection;
import org.lms.assignmentview.domain.tag.TagId;

public record DiscussionSelectionDto(
        @JsonProperty("tagID")
        @NonNull String tagId,

        int startIndex,

        int endIndex
) {

    static @NonNull DiscussionSelectionDto from(@NonNull DiscussionSelection discussionSelection) {
        return new DiscussionSelectionDto(discussionSelection.tagId().id(), discussionSelection.startIndex(),
                discussionSelection.endIndex());
    }

    @NonNull DiscussionSelection toDomain() {
        return new DiscussionSelection(new TagId(tagId), startIndex, endIndex);
    }

}
