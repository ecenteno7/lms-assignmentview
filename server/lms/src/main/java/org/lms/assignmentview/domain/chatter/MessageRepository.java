package org.lms.assignmentview.domain.chatter;

import lombok.NonNull;
import org.lms.assignmentview.domain.course.CourseId;

import java.util.List;

public interface MessageRepository {

    @NonNull
    Message save(@NonNull final Message message);

    @NonNull
    List<Message> getMessagesByCourseId(@NonNull final CourseId courseId);

}
