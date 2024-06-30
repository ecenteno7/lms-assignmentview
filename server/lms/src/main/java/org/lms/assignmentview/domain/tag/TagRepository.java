package org.lms.assignmentview.domain.tag;

import org.springframework.lang.NonNull;

import java.util.Set;

public interface TagRepository {

    @NonNull
    Set<Tag> findAllByIds(@NonNull final Set<TagId> tagIds);

}
