package org.lms.assignmentview.presentation.rest;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.application.TagApplicationService;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.tag.Tag;
import org.lms.assignmentview.domain.tag.command.CreateTagCommand;
import org.lms.assignmentview.presentation.rest.dto.tag.TagRequestDto;
import org.lms.assignmentview.presentation.rest.dto.tag.TagResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class TagController {

    @NonNull
    private final TagApplicationService tagApplicationService;

    @PostMapping("/api/courses/{course-id}/tags")
    public @NonNull TagResponseDto createTag(
            @PathVariable("course-id") @NonNull String courseIdStr,
            @RequestBody @NonNull TagRequestDto tagRequestDto
    ) {
        final CourseId courseId = new CourseId(courseIdStr);
        final List<CreateTagCommand> createTagCommands = tagRequestDto.toCreateTagCommands(courseId);
        final List<Tag> createdTags = tagApplicationService.createTags(createTagCommands);
        return TagResponseDto.from(createdTags);
    }

    @GetMapping("/api/courses/{course-id}/tags")
    public @NonNull TagResponseDto getCourseTags(
            @PathVariable("course-id") @NonNull String courseIdStr
    ) {
        final CourseId courseId = new CourseId(courseIdStr);
        final List<Tag> tags = tagApplicationService.getCourseTags(courseId);
        return TagResponseDto.from(tags);
    }

}
