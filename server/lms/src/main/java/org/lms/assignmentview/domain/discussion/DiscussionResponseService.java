package org.lms.assignmentview.domain.discussion;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.domain.discussion.command.CreateDiscussionResponseCommand;
import org.lms.assignmentview.domain.discussion.command.UpdateDiscussionResponseCommand;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DiscussionResponseService {

    @NonNull
    private final DiscussionResponseRepository discussionResponseRepository;

    @NonNull
    final DiscussionPostRepository discussionPostRepository;

    public @NonNull List<DiscussionResponse> createDiscussionResponses(
            @NonNull List<CreateDiscussionResponseCommand> createDiscussionResponseCommands
    ) {
        final List<DiscussionResponse> discussionResponses = createDiscussionResponseCommands.stream()
                .map(DiscussionResponse::createDiscussionResponse)
                .toList();
        return discussionResponseRepository.saveAllResponses(discussionResponses);
    }

    public @NonNull DiscussionResponse updateDiscussionResponse(
            @NonNull final UpdateDiscussionResponseCommand command
    ) {
        final DiscussionResponse discussionResponse =
                discussionResponseRepository.getDiscussionResponseById(command.discussionResponseId()).orElseThrow();
        final DiscussionPost discussionPost =
                discussionPostRepository.findById(discussionResponse.getParentPostId()).orElseThrow();
        final DiscussionResponse updatedDiscussionResponse =
                discussionResponse.updateDiscussionResponse(command, discussionPost);
        return discussionResponseRepository.save(updatedDiscussionResponse);
    }

}
