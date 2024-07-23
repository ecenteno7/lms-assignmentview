package org.lms.assignmentview.infrastructure.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.discussion.DiscussionPost;
import org.lms.assignmentview.domain.discussion.DiscussionPostId;
import org.lms.assignmentview.domain.discussion.DiscussionSelection;
import org.lms.assignmentview.domain.tag.TagId;
import org.lms.assignmentview.domain.user.User;
import org.lms.assignmentview.domain.user.UserId;
import org.springframework.lang.Nullable;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@SuperBuilder
@Entity(name = "discussion_post")
public class DiscussionPostEntity {

    @Id
    @NonNull
    private UUID id;

    @Column(name = "author_id", updatable = false)
    @NonNull
    private String authorId;

    @Column(name = "class_id", updatable = false)
    @NonNull
    private String classId;

    @Column(name = "created_on", updatable = false)
    @NonNull
    private OffsetDateTime createdOn;

    @Column(name = "updated_on")
    @Nullable
    private OffsetDateTime updatedOn;

    @Column(name = "title")
    @NonNull
    private String title;

    @Column(name = "content")
    @NonNull
    private String content;

    @Column(name = "vote_count")
    private int voteCount;

    @Column(name = "selection_reference_id")
    @Nullable
    private UUID selectionReferenceId;

    @Column(name = "selection_start_index")
    @Nullable
    private Integer selectionStartIndex;

    @Column(name = "selection_end_index")
    @Nullable
    private Integer selectionEndIndex;

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
                .selectionReferenceId(discussionPost.getDiscussionSelection()
                        .map(discussionSelection -> UUID.fromString(discussionSelection.tagId().id()))
                        .orElse(null))
                .selectionStartIndex(discussionPost.getDiscussionSelection()
                        .map(DiscussionSelection::startIndex)
                        .orElse(null))
                .selectionEndIndex(discussionPost.getDiscussionSelection()
                        .map(DiscussionSelection::endIndex)
                        .orElse(null))
                .build();
    }

    public @NonNull DiscussionPost toDomain() {
        DiscussionSelection discussionSelection = null;
        if (selectionReferenceId != null && selectionStartIndex != null && selectionEndIndex != null) {
            discussionSelection = new DiscussionSelection(new TagId(selectionReferenceId.toString()),
                    selectionStartIndex, selectionEndIndex);
        }
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
                .discussionSelection(discussionSelection)
                .build();
    }

}
