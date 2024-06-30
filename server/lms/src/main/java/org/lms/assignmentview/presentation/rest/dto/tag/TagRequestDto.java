package org.lms.assignmentview.presentation.rest.dto.tag;

import lombok.NonNull;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.tag.command.CreateTagCommand;

import java.util.List;

public record TagRequestDto(
        @NonNull List<TagDto> tags
) {

    public @NonNull List<CreateTagCommand> toCreateTagCommands(@NonNull final CourseId courseId) {
        return tags.stream()
                .map(tagDto -> tagDto.toCommand(courseId))
                .toList();
    }

}
