package org.lms.assignmentview.application;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.domain.discussion.DiscussionPost;
import org.lms.assignmentview.domain.discussion.DiscussionPostService;
import org.lms.assignmentview.domain.discussion.DiscussionPostView;
import org.lms.assignmentview.domain.discussion.command.CreateDiscussionPostCommand;
import org.lms.assignmentview.domain.discussion.command.GetDiscussionPostByIdCommand;
import org.lms.assignmentview.domain.discussion.command.GetDiscussionPostsCommand;
import org.lms.assignmentview.domain.user.User;
import org.lms.assignmentview.domain.user.UserDetails;
import org.lms.assignmentview.domain.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DiscussionPostApplicationService {

    @NonNull
    private final DiscussionPostService discussionPostService;

    @NonNull
    private final UserService userService;

    @Transactional
    public @NonNull DiscussionPostView createDiscussionPost(
            @NonNull final CreateDiscussionPostCommand createDiscussionPostCommand
    ) {
        final DiscussionPost discussionPost = discussionPostService.createDiscussionPost(createDiscussionPostCommand);
        final UserDetails userDetails = userService.findByUser(discussionPost.getAuthor());
        return new DiscussionPostView(discussionPost, userDetails, false);
    }

    @Transactional(readOnly = true)
    public @NonNull List<DiscussionPostView> getClassDiscussionPosts(
            @NonNull final GetDiscussionPostsCommand getDiscussionPostsCommand
    ) {
        final List<DiscussionPost> discussionPosts =
                discussionPostService.getClassDiscussionPosts(getDiscussionPostsCommand);
        final Set<User> users = discussionPosts.stream()
                .map(DiscussionPost::getAuthor)
                .collect(Collectors.toSet());
        final Map<User, UserDetails> userDetailsMap = userService.findByUsers(users);
        return discussionPosts.stream()
                .map(discussionPost -> new DiscussionPostView(discussionPost,
                        userDetailsMap.get(discussionPost.getAuthor()), false))
                .toList();
    }

    @Transactional(readOnly = true)
    public @NonNull DiscussionPostView getDiscussionPostById(
            @NonNull final GetDiscussionPostByIdCommand getDiscussionPostByIdCommand
    ) {
        final DiscussionPost discussionPost = discussionPostService.getDiscussionPostById(getDiscussionPostByIdCommand);
        final UserDetails userDetails = userService.findByUser(discussionPost.getAuthor());
        return new DiscussionPostView(discussionPost, userDetails, true);
    }

}
