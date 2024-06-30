package org.lms.assignmentview.domain.tag;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class TagService {

    @NonNull
    private final TagRepository tagRepository;

    public @NonNull Set<Tag> findAllByIds(@NonNull final Set<TagId> tagIds) {
        return tagRepository.findAllByIds(tagIds);
    }

}
