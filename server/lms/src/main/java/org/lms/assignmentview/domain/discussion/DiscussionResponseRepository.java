package org.lms.assignmentview.domain.discussion;

import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface DiscussionResponseRepository {

    @NonNull
    DiscussionResponse save(@NonNull final DiscussionResponse discussionResponse);

    @NonNull
    List<DiscussionResponse> saveAllResponses(@NonNull final List<DiscussionResponse> discussionResponses);

    @NonNull
    Optional<DiscussionResponse> getDiscussionResponseById(@NonNull final DiscussionResponseId discussionResponseId);

}
