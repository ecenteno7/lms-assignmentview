package org.lms.assignmentview.presentation.rest.dto.discussion.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.NonNull;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.discussion.DiscussionPostId;
import org.lms.assignmentview.domain.discussion.DiscussionResponse;
import org.lms.assignmentview.domain.discussion.DiscussionResponseId;
import org.lms.assignmentview.domain.discussion.command.CreateDiscussionResponseCommand;
import org.lms.assignmentview.domain.user.User;
import org.lms.assignmentview.domain.user.UserId;
import org.springframework.lang.Nullable;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Builder
public record DiscussionResponseDto(
        @JsonProperty("discussionResponseID")
        @Nullable String discussionResponseId,

        @JsonProperty("parentResponseID")
        @Nullable String parentResponseId,

        @JsonProperty("authorID")
        @NonNull String authorId,

        @JsonProperty("classID")
        @NonNull String classId,

        @Nullable OffsetDateTime createdOn,

        @Nullable OffsetDateTime updatedOn,

        @NonNull String content,

        int voteCount,

        @Nullable List<DiscussionResponseDto> responses
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

    public @NonNull CreateDiscussionResponseCommand toCreateDiscussionResponseCommand(
            @NonNull final DiscussionPostId parentPostId,
            @NonNull final CourseId courseId
    ) {
        return CreateDiscussionResponseCommand.builder()
                .parentPostId(parentPostId)
                .parentResponseId(Optional.ofNullable(parentResponseId).map(DiscussionResponseId::new).orElse(null))
                .author(new User(new UserId(authorId), courseId))
                .content(content)
                .build();
    }

}
