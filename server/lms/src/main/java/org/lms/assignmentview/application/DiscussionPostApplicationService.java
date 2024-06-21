package org.lms.assignmentview.application;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.domain.discussion.DiscussionPost;
import org.lms.assignmentview.domain.discussion.DiscussionPostService;
import org.lms.assignmentview.domain.discussion.command.CreateDiscussionPostCommand;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class DiscussionPostApplicationService {

    @NonNull
    private final DiscussionPostService discussionPostService;

    @Transactional
    public @NonNull DiscussionPost createDiscussionPost(
            @NonNull final CreateDiscussionPostCommand createDiscussionPostCommand
    ) {
        return discussionPostService.createDiscussionPost(createDiscussionPostCommand);
    }

}
