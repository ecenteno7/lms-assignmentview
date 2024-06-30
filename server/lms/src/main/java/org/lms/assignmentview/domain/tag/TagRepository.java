package org.lms.assignmentview.domain.tag;

import org.lms.assignmentview.domain.course.CourseId;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Set;

public interface TagRepository {

    @NonNull
    Set<Tag> findAllByIds(@NonNull final Set<TagId> tagIds);

    @NonNull
    List<Tag> getCourseTags(@NonNull final CourseId courseId);

}
