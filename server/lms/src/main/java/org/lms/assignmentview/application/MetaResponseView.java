package org.lms.assignmentview.application;

import lombok.NonNull;
import org.lms.assignmentview.domain.tag.Tag;

import java.util.List;

public record MetaResponseView(
        @NonNull List<Tag> tags
) {
}
