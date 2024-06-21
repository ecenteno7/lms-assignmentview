package org.lms.assignmentview.domain.discussion;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.domain.DiscussionPostRepository;
import org.lms.assignmentview.domain.discussion.command.CreateDiscussionPostCommand;
import org.springframework.stereotype.Service;

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

}
