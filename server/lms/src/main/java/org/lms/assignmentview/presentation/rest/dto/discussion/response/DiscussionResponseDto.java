package org.lms.assignmentview.presentation.rest.dto.discussion.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.NonNull;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.discussion.DiscussionPostId;
import org.lms.assignmentview.domain.discussion.DiscussionPostsView;
import org.lms.assignmentview.domain.discussion.DiscussionResponse;
import org.lms.assignmentview.domain.discussion.DiscussionResponseId;
import org.lms.assignmentview.domain.discussion.command.CreateDiscussionResponseCommand;
import org.lms.assignmentview.domain.discussion.command.UpdateDiscussionResponseCommand;
import org.lms.assignmentview.domain.user.User;
import org.lms.assignmentview.domain.user.UserDetails;
import org.lms.assignmentview.domain.user.UserId;
import org.springframework.lang.Nullable;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Builder(toBuilder = true)
public record DiscussionResponseDto(
        @JsonProperty("discussionResponseID")
        @Nullable String discussionResponseId,

        @JsonProperty("parentResponseID")
        @Nullable String parentResponseId,

        @JsonProperty("authorID")
        @Nullable String authorId,

        @Nullable String firstName,

        @Nullable String lastName,

        @JsonProperty("classID")
        @Nullable String classId,

        @Nullable OffsetDateTime createdOn,

        @Nullable OffsetDateTime updatedOn,

        @Nullable String content,

        int voteCount,

        @Nullable Boolean accepted,

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
                .accepted(discussionResponse.isAccepted())
                .build();
    }

    public static DiscussionResponseDto from(@NonNull final DiscussionResponse discussionResponse,
                                             @NonNull final DiscussionPostsView discussionPostsView) {
        final UserDetails userDetails = discussionPostsView.getUserDetailsFor(discussionResponse.getAuthor());
        return DiscussionResponseDto.from(discussionResponse).toBuilder()
                .firstName(userDetails.getFirstName())
                .lastName(userDetails.getLastName())
                .responses(discussionResponse.getResponses().stream()
                        .map(response -> DiscussionResponseDto.from(response, discussionPostsView))
                        .toList())
                .build();
    }

    public @NonNull CreateDiscussionResponseCommand toCreateDiscussionResponseCommand(
            @NonNull final DiscussionPostId parentPostId,
            @NonNull final CourseId courseId
    ) {
        if (Objects.isNull(authorId)) {
            throw new IllegalArgumentException("authorID cannot be null");
        }
        if (Objects.isNull(content)) {
            throw new IllegalArgumentException("content cannot be null");
        }
        return CreateDiscussionResponseCommand.builder()
                .parentPostId(parentPostId)
                .parentResponseId(Optional.ofNullable(parentResponseId).map(DiscussionResponseId::new).orElse(null))
                .author(new User(new UserId(authorId), courseId))
                .content(content)
                .accepted(Optional.ofNullable(accepted).orElse(false))
                .build();
    }

    public @NonNull UpdateDiscussionResponseCommand toUpdateDiscussionResponseCommand() {
        if (Objects.isNull(discussionResponseId)) {
            throw new IllegalArgumentException("discussionResponseID cannot be null");
        }
        return UpdateDiscussionResponseCommand.builder()
                .discussionResponseId(new DiscussionResponseId(discussionResponseId))
                .content(content)
                .accepted(accepted)
                .build();
    }

}
