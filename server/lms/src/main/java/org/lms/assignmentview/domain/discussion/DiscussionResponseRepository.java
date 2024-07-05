package org.lms.assignmentview.domain.discussion;

import lombok.NonNull;

import java.util.List;

public interface DiscussionResponseRepository {

    @NonNull
    List<DiscussionResponse> saveAllResponses(@NonNull List<DiscussionResponse> discussionResponses);

}
