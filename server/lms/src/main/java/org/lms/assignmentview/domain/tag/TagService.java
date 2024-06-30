package org.lms.assignmentview.domain.tag;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.tag.command.CreateTagCommand;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class TagService {

    @NonNull
    private final TagRepository tagRepository;

    public @NonNull Set<Tag> findAllByIds(@NonNull final Set<TagId> tagIds) {
        return tagRepository.findAllByIds(tagIds);
    }

    public @NonNull List<Tag> getCourseTags(@NonNull final CourseId courseId) {
        return tagRepository.getCourseTags(courseId);
    }

    public @NonNull List<Tag> createTags(@NonNull final List<CreateTagCommand> createTagCommands) {
        final List<Tag> tags = createTagCommands.stream()
                .map(Tag::createTag)
                .toList();
        return tagRepository.saveAll(tags);
    }

}
