package org.lms.assignmentview.application;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.tag.Tag;
import org.lms.assignmentview.domain.tag.TagService;
import org.lms.assignmentview.domain.tag.command.CreateTagCommand;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class TagApplicationService {

    @NonNull
    private final TagService tagService;

    @Transactional
    public @NonNull List<Tag> createTags(@NonNull final List<CreateTagCommand> createTagCommands) {
        return tagService.createTags(createTagCommands);
    }

    @Transactional(readOnly = true)
    public @NonNull List<Tag> getCourseTags(@NonNull final CourseId courseId) {
        return tagService.getCourseTags(courseId);
    }

}
