package org.lms.assignmentview.presentation.rest;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.application.DiscussionResponseApplicationService;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.discussion.DiscussionPostId;
import org.lms.assignmentview.domain.discussion.DiscussionResponse;
import org.lms.assignmentview.domain.discussion.DiscussionResponseId;
import org.lms.assignmentview.domain.discussion.command.CreateDiscussionResponseCommand;
import org.lms.assignmentview.domain.discussion.command.UpdateDiscussionResponseCommand;
import org.lms.assignmentview.presentation.rest.dto.discussion.response.DiscussionResponsesRequestDto;
import org.lms.assignmentview.presentation.rest.dto.discussion.response.DiscussionResponsesResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class DiscussionResponseController {

    @NonNull
    private final DiscussionResponseApplicationService discussionResponseApplicationService;

    @PostMapping("/api/courses/{course-id}/discussion-posts/{discussion-post-id}/responses")
    public @NonNull DiscussionResponsesResponseDto createDiscussionResponse(
            @PathVariable("course-id") @NonNull final String courseIdStr,
            @PathVariable("discussion-post-id") @NonNull final String discussionPostIdStr,
            @RequestBody @NonNull DiscussionResponsesRequestDto discussionResponsesRequestDto
    ) {
        final CourseId courseId = new CourseId(courseIdStr);
        final DiscussionPostId discussionPostId = new DiscussionPostId(discussionPostIdStr);
        final List<CreateDiscussionResponseCommand> commands = discussionResponsesRequestDto.responses().stream()
                .map(response -> response.toCreateDiscussionResponseCommand(discussionPostId, courseId))
                .toList();
        final List<DiscussionResponse> discussionResponses =
                discussionResponseApplicationService.createDiscussionResponses(commands);
        return DiscussionResponsesResponseDto.from(discussionResponses);
    }

    @PatchMapping("/api/courses/{course-id}/discussion-posts/{discussion-post-id}/responses/{response-id}")
    public @NonNull DiscussionResponsesResponseDto updateDiscussionResponse(
            @PathVariable("course-id") @NonNull final String courseIdStr,
            @PathVariable("discussion-post-id") @NonNull final String discussionPostIdStr,
            @PathVariable("response-id") @NonNull final String discussionResponseIdStr,
            @RequestBody @NonNull DiscussionResponsesRequestDto discussionResponsesRequestDto
    ) {
        if (discussionResponsesRequestDto.responses().size() != 1) {
            throw new IllegalArgumentException("Updating a single response is only supported.");
        }
        final CourseId courseId = new CourseId(courseIdStr);
        final DiscussionPostId discussionPostId = new DiscussionPostId(discussionPostIdStr);
        final DiscussionResponseId discussionResponseId = new DiscussionResponseId(discussionResponseIdStr);
        final UpdateDiscussionResponseCommand updateDiscussionResponseCommand =
                discussionResponsesRequestDto.responses().get(0).toUpdateDiscussionResponseCommand();
        final DiscussionResponse updatedDiscussionResponse =
                discussionResponseApplicationService.updateDiscussionResponse(updateDiscussionResponseCommand);
        return DiscussionResponsesResponseDto.from(List.of(updatedDiscussionResponse));
    }

}
