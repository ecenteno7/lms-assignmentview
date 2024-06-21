package org.lms.assignmentview.infrastructure.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.lms.assignmentview.domain.discussion.DiscussionResponse;
import org.lms.assignmentview.domain.discussion.DiscussionResponseId;
import org.lms.assignmentview.domain.user.User;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@SuperBuilder
@Entity(name = "discussion_response")
public class DiscussionResponseEntity {

    @Id
    private UUID id;

    @Setter
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private DiscussionPostEntity discussionPost;

//    @Column(name = "parent_response_id")
//    private String parentResponseId;

    @Setter
    @ManyToOne
    @JoinColumn(name = "parent_response_id")
    private DiscussionResponseEntity parent;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent", orphanRemoval = true, fetch = FetchType.LAZY)
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

    public static @NonNull DiscussionResponseEntity from(@NonNull final DiscussionResponse discussionResponse) {
        return DiscussionResponseEntity.builder()
                .id(UUID.fromString(discussionResponse.getId().id()))
                .authorId(discussionResponse.getAuthor().userId())
                .classId(discussionResponse.getAuthor().classId())
                .createdOn(discussionResponse.getCreatedOn())
                .updatedOn(discussionResponse.getUpdatedOn().orElse(null))
                .content(discussionResponse.getContent())
                .voteCount(discussionResponse.getVoteCount())
                .build();
    }

    public @NonNull DiscussionResponse toDomain() {
        return DiscussionResponse.builder()
                .id(new DiscussionResponseId(id.toString()))
                .author(new User(authorId, classId))
                .createdOn(createdOn)
                .updatedOn(updatedOn)
                .content(content)
                .voteCount(voteCount)
                .responses(responses.stream()
                        .map(DiscussionResponseEntity::toDomain)
                        .toList())
                .build();
    }

}
