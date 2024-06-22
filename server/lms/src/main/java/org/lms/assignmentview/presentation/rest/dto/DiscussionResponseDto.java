package org.lms.assignmentview.presentation.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.NonNull;
import org.lms.assignmentview.domain.discussion.DiscussionResponse;
import org.springframework.lang.Nullable;

import java.time.OffsetDateTime;
import java.util.List;

@Builder
public record DiscussionResponseDto(
        @JsonProperty("discussionResponseID")
        @NonNull String discussionResponseId,

        @JsonProperty("authorID")
        @NonNull String authorId,

        @JsonProperty("classID")
        @NonNull String classId,

        @NonNull OffsetDateTime createdOn,

        @Nullable OffsetDateTime updatedOn,

        @NonNull String content,

        int voteCount,

        @NonNull List<DiscussionResponseDto> responses
) {

    public static DiscussionResponseDto from(@NonNull final DiscussionResponse discussionResponse) {
        return DiscussionResponseDto.builder()
                .discussionResponseId(discussionResponse.getId().id())
                .authorId(discussionResponse.getAuthor().userId().id())
                .classId(discussionResponse.getAuthor().classId().id())
                .createdOn(discussionResponse.getCreatedOn())
                .updatedOn(discussionResponse.getUpdatedOn().orElse(null))
                .content(discussionResponse.getContent())
                .voteCount(discussionResponse.getVoteCount())
                .responses(discussionResponse.getResponses().stream()
                        .map(DiscussionResponseDto::from)
                        .toList())
                .build();
    }

}
