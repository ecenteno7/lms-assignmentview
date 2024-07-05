package org.lms.assignmentview.infrastructure.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.discussion.DiscussionPost;
import org.lms.assignmentview.domain.discussion.DiscussionPostId;
import org.lms.assignmentview.domain.user.User;
import org.lms.assignmentview.domain.user.UserId;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@SuperBuilder
@Entity(name = "discussion_post")
public class DiscussionPostEntity {

    @Id
    private UUID id;

    @Column(name = "author_id", updatable = false)
    private String authorId;

    @Column(name = "class_id", updatable = false)
    private String classId;

    @Column(name = "created_on", updatable = false)
    private OffsetDateTime createdOn;

    @Column(name = "updated_on")
    private OffsetDateTime updatedOn;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "vote_count")
    private int voteCount;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "discussionPostId", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<DiscussionResponseEntity> discussionResponses;

    @JoinTable(
            name = "post_tags",
            joinColumns = @JoinColumn(name = "discussion_post_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id")
    )
    @ManyToMany
    private List<TagEntity> tags;

    public static @NonNull DiscussionPostEntity from(@NonNull final DiscussionPost discussionPost) {
        return DiscussionPostEntity.builder()
                .id(UUID.fromString(discussionPost.getId().id()))
                .authorId(discussionPost.getAuthor().userId().id())
                .classId(discussionPost.getAuthor().classId().id())
                .createdOn(discussionPost.getCreatedOn())
                .updatedOn(discussionPost.getUpdatedOn().orElse(null))
                .title(discussionPost.getTitle())
                .content(discussionPost.getContent())
                .voteCount(discussionPost.getVoteCount())
                .discussionResponses(discussionPost.getResponses().stream()
                        .map(DiscussionResponseEntity::from)
                        .toList())
                .tags(discussionPost.getTags().stream()
                        .map(TagEntity::from)
                        .toList())
                .build();
    }

    public @NonNull DiscussionPost toDomain() {
        return DiscussionPost.builder()
                .id(new DiscussionPostId(id.toString()))
                .author(new User(new UserId(authorId), new CourseId(classId)))
                .createdOn(createdOn)
                .updatedOn(updatedOn)
                .title(title)
                .content(content)
                .voteCount(voteCount)
                .responses(discussionResponses.stream()
                        .map(DiscussionResponseEntity::toDomain)
                        .toList())
                .tags(tags.stream()
                        .map(TagEntity::toDomain)
                        .toList())
                .build();
    }

}
