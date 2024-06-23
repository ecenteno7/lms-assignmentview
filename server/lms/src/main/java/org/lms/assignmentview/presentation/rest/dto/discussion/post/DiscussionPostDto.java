package org.lms.assignmentview.presentation.rest.dto.discussion.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.discussion.DiscussionPost;
import org.lms.assignmentview.domain.discussion.DiscussionPostView;
import org.lms.assignmentview.domain.discussion.command.CreateDiscussionPostCommand;
import org.lms.assignmentview.domain.user.User;
import org.lms.assignmentview.domain.user.UserId;
import org.lms.assignmentview.presentation.rest.dto.discussion.response.DiscussionResponseDto;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.time.OffsetDateTime;
import java.util.List;

@Builder(toBuilder = true)
public record DiscussionPostDto(
        @JsonProperty("discussionPostID")
        @Nullable String discussionPostId,

        @JsonProperty("authorID")
        @NonNull String authorId,

        @NonNull String firstName,

        @NonNull String lastName,

        @Nullable OffsetDateTime createdOn,

        @Nullable OffsetDateTime updatedOn,

        @NonNull String title,

        @NonNull String content,

        int voteCount,

        @Nullable List<DiscussionResponseDto> responses
) {

    public static @NonNull DiscussionPostDto from(@NonNull final DiscussionPostView discussionPostView) {
        final DiscussionPost discussionPost = discussionPostView.discussionPost();
        return DiscussionPostDto.builder()
                .discussionPostId(discussionPost.getId().id())
                .authorId(discussionPost.getAuthor().userId().id())
                .firstName(discussionPostView.userDetails().getFirstName())
                .lastName(discussionPostView.userDetails().getLastName())
                .createdOn(discussionPost.getCreatedOn())
                .updatedOn(discussionPost.getUpdatedOn().orElse(null))
                .title(discussionPost.getTitle())
                .content(discussionPost.getContent())
                .voteCount(discussionPost.getVoteCount())
                .responses(discussionPost.getResponses().stream()
                        .map(DiscussionResponseDto::from)
                        .toList())
                .build();
    }

    public @NonNull CreateDiscussionPostCommand toCreateDiscussionPostCommand(@NonNull final CourseId courseId) {
        return new CreateDiscussionPostCommand(new User(new UserId(authorId), courseId), title, content);
    }

}
