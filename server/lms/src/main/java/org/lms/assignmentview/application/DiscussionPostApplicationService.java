package org.lms.assignmentview.application;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.domain.discussion.DiscussionPost;
import org.lms.assignmentview.domain.discussion.DiscussionPostService;
import org.lms.assignmentview.domain.discussion.DiscussionPostsView;
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
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

@Service
@AllArgsConstructor
public class DiscussionPostApplicationService {

    @NonNull
    private final DiscussionPostService discussionPostService;

    @NonNull
    private final UserService userService;

    @Transactional
    public @NonNull DiscussionPostsView createDiscussionPosts(
            @NonNull final List<CreateDiscussionPostCommand> createDiscussionPostCommands
    ) {
        final List<DiscussionPost> discussionPosts =
                discussionPostService.createDiscussionPosts(createDiscussionPostCommands);
        final Map<User, UserDetails> userDetailsByUserId = discussionPosts.stream()
                .map(DiscussionPost::getAuthor)
                .distinct()
                .map(userService::findByUser)
                .collect(toMap(UserDetails::getUser, Function.identity()));
        return new DiscussionPostsView(discussionPosts, userDetailsByUserId, false);
    }

    @Transactional(readOnly = true)
    public @NonNull DiscussionPostsView getClassDiscussionPosts(
            @NonNull final GetDiscussionPostsCommand getDiscussionPostsCommand
    ) {
        final List<DiscussionPost> discussionPosts =
                discussionPostService.getClassDiscussionPosts(getDiscussionPostsCommand);
        final Set<User> users = discussionPosts.stream()
                .map(DiscussionPost::getAuthor)
                .collect(Collectors.toSet());
        final Map<User, UserDetails> userDetailsMap = userService.findByUsers(users);
        return new DiscussionPostsView(discussionPosts, userDetailsMap, false);
    }

    @Transactional(readOnly = true)
    public @NonNull DiscussionPostsView getDiscussionPostById(
            @NonNull final GetDiscussionPostByIdCommand getDiscussionPostByIdCommand
    ) {
        final DiscussionPost discussionPost = discussionPostService.getDiscussionPostById(getDiscussionPostByIdCommand);
        final UserDetails userDetails = userService.findByUser(discussionPost.getAuthor());
        return new DiscussionPostsView(List.of(discussionPost), Map.of(userDetails.getUser(), userDetails), true);
    }

}
