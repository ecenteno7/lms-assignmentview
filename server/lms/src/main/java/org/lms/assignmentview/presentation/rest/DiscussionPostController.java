package org.lms.assignmentview.presentation.rest;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.application.DiscussionPostApplicationService;
import org.lms.assignmentview.domain.discussion.DiscussionPost;
import org.lms.assignmentview.domain.discussion.command.CreateDiscussionPostCommand;
import org.lms.assignmentview.presentation.rest.dto.DiscussionPostDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class DiscussionPostController {

    @NonNull
    private final DiscussionPostApplicationService discussionPostApplicationService;

    @PostMapping("/discussion-post")
    public DiscussionPostDto createDiscussionPost(@RequestBody final DiscussionPostDto discussionPostDto) {
        final CreateDiscussionPostCommand createDiscussionPostCommand =
                discussionPostDto.toCreateDiscussionPostCommand();
        final DiscussionPost createdDiscussionPost =
                discussionPostApplicationService.createDiscussionPost(createDiscussionPostCommand);
        return DiscussionPostDto.from(createdDiscussionPost);
    }

}
