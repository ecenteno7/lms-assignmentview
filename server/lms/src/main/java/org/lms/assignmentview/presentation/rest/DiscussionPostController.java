package org.lms.assignmentview.presentation.rest;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.application.DiscussionPostApplicationService;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.discussion.DiscussionPostView;
import org.lms.assignmentview.domain.discussion.command.CreateDiscussionPostCommand;
import org.lms.assignmentview.presentation.rest.dto.discussion.post.DiscussionPostRequestDto;
import org.lms.assignmentview.presentation.rest.dto.discussion.post.DiscussionPostResponseDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class DiscussionPostController {

    @NonNull
    private final DiscussionPostApplicationService discussionPostApplicationService;

    @PostMapping("/api/courses/{course-id}/discussion-post")
    public @NonNull DiscussionPostResponseDto createDiscussionPost(
            @PathVariable("course-id") @NonNull final String courseIdStr,
            @RequestBody @NonNull final DiscussionPostRequestDto discussionPostRequestDto
    ) {
        if (discussionPostRequestDto.discussionPosts().size() != 1) {
            throw new IllegalArgumentException("Application only supports creating a single post.");
        }
        final CourseId courseId = new CourseId(courseIdStr);
        final CreateDiscussionPostCommand createDiscussionPostCommand =
                discussionPostRequestDto.discussionPosts().get(0).toCreateDiscussionPostCommand(courseId);
        final DiscussionPostView createdDiscussionPostView =
                discussionPostApplicationService.createDiscussionPost(createDiscussionPostCommand);
        return DiscussionPostResponseDto.from(List.of(createdDiscussionPostView));
    }

}
