package org.lms.assignmentview.domain.discussion;

import org.lms.assignmentview.domain.tag.TagId;
import org.springframework.lang.NonNull;

public record DiscussionSelection(
        @NonNull TagId tagId,
        int startIndex,
        int endIndex
) {
}
