package org.lms.assignmentview.domain.discussion;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.domain.discussion.command.CreateDiscussionPostCommand;
import org.lms.assignmentview.domain.discussion.command.GetDiscussionPostByIdCommand;
import org.lms.assignmentview.domain.discussion.command.GetDiscussionPostsCommand;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class DiscussionPostService {

    @NonNull
    private final DiscussionPostRepository discussionPostRepository;

    public @NonNull DiscussionPost createDiscussionPost(
            @NonNull final CreateDiscussionPostCommand createDiscussionPostCommand
    ) {
        final DiscussionPost discussionPost = DiscussionPost.createDiscussionPost(createDiscussionPostCommand);
        return discussionPostRepository.save(discussionPost);
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

}
