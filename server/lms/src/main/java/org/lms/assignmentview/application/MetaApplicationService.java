package org.lms.assignmentview.application;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.tag.Tag;
import org.lms.assignmentview.domain.tag.TagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class MetaApplicationService {

    @NonNull
    private final TagService tagService;

    @Transactional(readOnly = true)
    public @NonNull MetaResponseView getCourseMeta(@NonNull final CourseId courseId) {
        final List<Tag> tags = tagService.getCourseTags(courseId);
        return new MetaResponseView(tags);
    }

}
