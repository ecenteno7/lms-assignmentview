package org.lms.assignmentview.domain.discussion;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.domain.discussion.command.CreateDiscussionPostCommand;
import org.lms.assignmentview.domain.discussion.command.GetDiscussionPostByIdCommand;
import org.lms.assignmentview.domain.discussion.command.GetDiscussionPostsCommand;
import org.lms.assignmentview.domain.tag.Tag;
import org.lms.assignmentview.domain.tag.TagId;
import org.lms.assignmentview.domain.tag.TagService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

@Service
@AllArgsConstructor
public class DiscussionPostService {

    @NonNull
    private final DiscussionPostRepository discussionPostRepository;

    @NonNull
    private final TagService tagService;

    public @NonNull List<DiscussionPost> createDiscussionPosts(
            @NonNull final List<CreateDiscussionPostCommand> createDiscussionPostCommands
    ) {
        final Set<TagId> tagIds = createDiscussionPostCommands.stream()
                .flatMap(CreateDiscussionPostCommand::getReferencedTagIds)
                .collect(Collectors.toSet());
        final Map<TagId, Tag> tagsById = tagService.findAllByIds(tagIds).stream()
                .collect(toMap(Tag::getId, Function.identity()));
        final List<DiscussionPost> discussionPosts = createDiscussionPostCommands.stream()
                .map(command -> DiscussionPost.createDiscussionPost(command, tagsById))
                .toList();
        return discussionPostRepository.saveAll(discussionPosts);
    }

    public @NonNull List<DiscussionPost> getClassDiscussionPosts(
            @NonNull final GetDiscussionPostsCommand getDiscussionPostsCommand
    ) {
        final List<DiscussionPost> discussionPosts =
                discussionPostRepository.findAllByCourseId(getDiscussionPostsCommand.courseId());
        return discussionPosts.stream()
                .sorted(Comparator.comparing(DiscussionPost::getCreatedOn).reversed())
                .toList();
    }

    public @NonNull DiscussionPost getDiscussionPostById(
            @NonNull final GetDiscussionPostByIdCommand getDiscussionPostByIdCommand
    ) {
        return discussionPostRepository.findById(getDiscussionPostByIdCommand.discussionPostId()).orElseThrow();
    }

    public @NonNull List<DiscussionPost> getDiscussionInsights(@NonNull final TagId tagId) {
        final List<DiscussionPost> allTaggedPosts = discussionPostRepository.findAllByTagId(tagId);
        return allTaggedPosts.stream()
                .map(DiscussionPost::withOnlyAcceptedResponses)
                .filter(discussionPost -> !discussionPost.getResponses().isEmpty())
                .toList();
    }

}
