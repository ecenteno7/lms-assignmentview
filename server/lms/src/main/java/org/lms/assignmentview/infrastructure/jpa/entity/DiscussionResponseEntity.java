package org.lms.assignmentview.infrastructure.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.discussion.DiscussionPostId;
import org.lms.assignmentview.domain.discussion.DiscussionResponse;
import org.lms.assignmentview.domain.discussion.DiscussionResponseId;
import org.lms.assignmentview.domain.user.User;
import org.lms.assignmentview.domain.user.UserId;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Getter
@NoArgsConstructor
@SuperBuilder
@Entity(name = "discussion_response")
public class DiscussionResponseEntity {

    @Id
    private UUID id;

    @Column(name = "discussion_post_id", updatable = false)
    private UUID discussionPostId;

    @Column(name = "parent_response_id", updatable = false)
    private UUID parentResponseId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parentResponseId", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<DiscussionResponseEntity> responses;

    @Column(name = "author_id", updatable = false)
    private String authorId;

    @Column(name = "class_id", updatable = false)
    private String classId;

    @Column(name = "created_on", updatable = false)
    private OffsetDateTime createdOn;

    @Column(name = "updated_on")
    private OffsetDateTime updatedOn;

    @Column(name = "content")
    private String content;

    @Column(name = "vote_count")
    private int voteCount;

    @Column(name = "accepted")
    private boolean accepted;

    public static @NonNull DiscussionResponseEntity from(@NonNull final DiscussionResponse discussionResponse) {
        return DiscussionResponseEntity.builder()
                .id(UUID.fromString(discussionResponse.getId().id()))
                .discussionPostId(UUID.fromString(discussionResponse.getParentPostId().id()))
                .parentResponseId(discussionResponse.getParentResponseId()
                        .map(parentResponseId -> UUID.fromString(parentResponseId.id())).orElse(null))
                .responses(discussionResponse.getResponses().stream()
                        .map(DiscussionResponseEntity::from)
                        .toList())
                .authorId(discussionResponse.getAuthor().userId().id())
                .classId(discussionResponse.getAuthor().classId().id())
                .createdOn(discussionResponse.getCreatedOn())
                .updatedOn(discussionResponse.getUpdatedOn().orElse(null))
                .content(discussionResponse.getContent())
                .voteCount(discussionResponse.getVoteCount())
                .accepted(discussionResponse.isAccepted())
                .build();
    }

    public @NonNull DiscussionResponse toDomain() {
        return DiscussionResponse.builder()
                .id(new DiscussionResponseId(id.toString()))
                .parentPostId(new DiscussionPostId(discussionPostId.toString()))
                .parentResponseId(Optional.ofNullable(parentResponseId)
                        .map(parentResponseId -> new DiscussionResponseId(parentResponseId.toString())).orElse(null))
                .author(new User(new UserId(authorId), new CourseId(classId)))
                .createdOn(createdOn)
                .updatedOn(updatedOn)
                .content(content)
                .voteCount(voteCount)
                .responses(Optional.ofNullable(responses).stream()
                        .flatMap(List::stream)
                        .map(DiscussionResponseEntity::toDomain)
                        .toList())
                .accepted(accepted)
                .build();
    }

}
