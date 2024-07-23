package org.lms.assignmentview.presentation.rest.dto.discussion.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.NonNull;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.discussion.DiscussionPost;
import org.lms.assignmentview.domain.discussion.DiscussionPostsView;
import org.lms.assignmentview.domain.discussion.DiscussionSelection;
import org.lms.assignmentview.domain.discussion.command.CreateDiscussionPostCommand;
import org.lms.assignmentview.domain.tag.TagId;
import org.lms.assignmentview.domain.user.User;
import org.lms.assignmentview.domain.user.UserDetails;
import org.lms.assignmentview.domain.user.UserId;
import org.lms.assignmentview.presentation.rest.dto.discussion.response.DiscussionResponseDto;
import org.lms.assignmentview.presentation.rest.dto.tag.TagDto;
import org.springframework.lang.Nullable;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Builder(toBuilder = true)
public record DiscussionPostDto(
        @JsonProperty("discussionPostID")
        @Nullable String discussionPostId,

        @JsonProperty("authorID")
        @NonNull String authorId,

        @Nullable String firstName,

        @Nullable String lastName,

        @Nullable OffsetDateTime createdOn,

        @Nullable OffsetDateTime updatedOn,

        @NonNull String title,

        @Nullable String content,

        int voteCount,

        @Nullable List<DiscussionResponseDto> responses,

        @Nullable List<TagDto> tags,

        @Nullable DiscussionSelectionDto selection
) {

    public static @NonNull DiscussionPostDto from(@NonNull final DiscussionPost discussionPost,
                                                  @NonNull final DiscussionPostsView discussionPostsView) {
        final UserDetails userDetails = discussionPostsView.getUserDetailsFor(discussionPost.getAuthor());
        DiscussionPostDtoBuilder discussionPostBuilder = DiscussionPostDto.builder()
                .discussionPostId(discussionPost.getId().id())
                .authorId(discussionPost.getAuthor().userId().id())
                .firstName(userDetails.getFirstName())
                .lastName(userDetails.getLastName())
                .createdOn(discussionPost.getCreatedOn())
                .updatedOn(discussionPost.getUpdatedOn().orElse(null))
                .title(discussionPost.getTitle())
                .voteCount(discussionPost.getVoteCount())
                .tags(discussionPost.getTags().stream()
                        .map(TagDto::from)
                        .toList())
                .selection(discussionPost.getDiscussionSelection()
                        .map(DiscussionSelectionDto::from)
                        .orElse(null));
        if (discussionPostsView.displayResponses()) {
            discussionPostBuilder = discussionPostBuilder
                    .content(discussionPost.getContent())
                    .responses(discussionPost.getResponses().stream()
                            .map(discussionResponse -> DiscussionResponseDto.from(discussionResponse,
                                    discussionPostsView))
                            .toList());
        }
        return discussionPostBuilder.build();
    }

    public @NonNull CreateDiscussionPostCommand toCreateDiscussionPostCommand(@NonNull final CourseId courseId) {
        if (Objects.isNull(content)) {
            throw new IllegalArgumentException("Content is required to create a discussion post.");
        }
        final List<TagId> tagIds = Optional.ofNullable(tags).stream()
                .flatMap(List::stream)
                .peek(tagDto -> {
                    if (Objects.isNull(tagDto.tagId())) {
                        throw new IllegalArgumentException("Tag id is required.");
                    }
                })
                .map(tagDto -> new TagId(tagDto.tagId()))
                .toList();
        final DiscussionSelection discussionSelection = Optional.ofNullable(selection)
                .map(DiscussionSelectionDto::toDomain)
                .orElse(null);
        return new CreateDiscussionPostCommand(new User(new UserId(authorId), courseId), title, content, tagIds,
                discussionSelection);
    }

}
