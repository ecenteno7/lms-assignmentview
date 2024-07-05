package org.lms.assignmentview.domain.discussion;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.domain.discussion.command.CreateDiscussionResponseCommand;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DiscussionResponseService {

    @NonNull
    private final DiscussionResponseRepository discussionResponseRepository;

    public @NonNull List<DiscussionResponse> createDiscussionResponses(
            @NonNull List<CreateDiscussionResponseCommand> createDiscussionResponseCommands
    ) {
        final List<DiscussionResponse> discussionResponses = createDiscussionResponseCommands.stream()
                .map(DiscussionResponse::createDiscussionResponse)
                .toList();
        return discussionResponseRepository.saveAllResponses(discussionResponses);
    }

}
