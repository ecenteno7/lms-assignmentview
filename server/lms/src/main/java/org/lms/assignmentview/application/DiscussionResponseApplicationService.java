package org.lms.assignmentview.application;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.domain.discussion.DiscussionResponse;
import org.lms.assignmentview.domain.discussion.DiscussionResponseService;
import org.lms.assignmentview.domain.discussion.command.CreateDiscussionResponseCommand;
import org.lms.assignmentview.domain.discussion.command.UpdateDiscussionResponseCommand;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class DiscussionResponseApplicationService {

    @NonNull
    private final DiscussionResponseService discussionResponseService;

    @Transactional
    public @NonNull List<DiscussionResponse> createDiscussionResponses(
            @NonNull final List<CreateDiscussionResponseCommand> createDiscussionResponseCommands
    ) {
        return discussionResponseService.createDiscussionResponses(createDiscussionResponseCommands);
    }

    @Transactional
    public @NonNull DiscussionResponse updateDiscussionResponse(
            @NonNull final UpdateDiscussionResponseCommand updateDiscussionResponseCommand
    ) {
        return discussionResponseService.updateDiscussionResponse(updateDiscussionResponseCommand);
    }

}
