package org.lms.assignmentview.application;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.domain.discussion.DiscussionPost;
import org.lms.assignmentview.domain.discussion.DiscussionPostService;
import org.lms.assignmentview.domain.discussion.DiscussionPostView;
import org.lms.assignmentview.domain.discussion.command.CreateDiscussionPostCommand;
import org.lms.assignmentview.domain.user.UserDetails;
import org.lms.assignmentview.domain.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return new DiscussionPostView(discussionPost, userDetails);
    }

}
