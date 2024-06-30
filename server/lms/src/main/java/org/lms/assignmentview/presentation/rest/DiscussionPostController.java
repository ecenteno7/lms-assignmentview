package org.lms.assignmentview.presentation.rest;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.application.DiscussionPostApplicationService;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.discussion.DiscussionPostId;
import org.lms.assignmentview.domain.discussion.DiscussionPostsView;
import org.lms.assignmentview.domain.discussion.command.CreateDiscussionPostCommand;
import org.lms.assignmentview.domain.discussion.command.GetDiscussionPostByIdCommand;
import org.lms.assignmentview.domain.discussion.command.GetDiscussionPostsCommand;
import org.lms.assignmentview.presentation.rest.dto.discussion.post.DiscussionPostRequestDto;
import org.lms.assignmentview.presentation.rest.dto.discussion.post.DiscussionPostResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class DiscussionPostController {

    @NonNull
    private final DiscussionPostApplicationService discussionPostApplicationService;

    @PostMapping("/api/courses/{course-id}/discussion-posts")
    public @NonNull DiscussionPostResponseDto createDiscussionPost(
            @PathVariable("course-id") @NonNull final String courseIdStr,
            @RequestBody @NonNull final DiscussionPostRequestDto discussionPostRequestDto
    ) {
        final CourseId courseId = new CourseId(courseIdStr);
        final List<CreateDiscussionPostCommand> createDiscussionPostCommands =
                discussionPostRequestDto.discussionPosts().stream()
                        .map(discussionPostDto -> discussionPostDto.toCreateDiscussionPostCommand(courseId))
                        .toList();
        final DiscussionPostsView createdDiscussionPostsView =
                discussionPostApplicationService.createDiscussionPosts(createDiscussionPostCommands);
        return DiscussionPostResponseDto.from(createdDiscussionPostsView);
    }

    @GetMapping("/api/courses/{course-id}/discussion-posts")
    public @NonNull DiscussionPostResponseDto getClassDiscussionPosts(
            @PathVariable("course-id") @NonNull final String courseIdStr
    ) {
        final CourseId courseId = new CourseId(courseIdStr);
        final GetDiscussionPostsCommand getDiscussionPostsCommand = new GetDiscussionPostsCommand(courseId);
        final DiscussionPostsView classDiscussionPosts =
                discussionPostApplicationService.getClassDiscussionPosts(getDiscussionPostsCommand);
        return DiscussionPostResponseDto.from(classDiscussionPosts);
    }

    @GetMapping("/api/courses/{course-id}/discussion-posts/{discussion-post-id}")
    public @NonNull DiscussionPostResponseDto getDiscussionPostById(
            @PathVariable("course-id") @NonNull final String courseIdStr,
            @PathVariable("discussion-post-id") @NonNull final String discussionPostIdStr
    ) {
        final CourseId courseId = new CourseId(courseIdStr);
        final DiscussionPostId discussionPostId = new DiscussionPostId(discussionPostIdStr);
        final GetDiscussionPostByIdCommand getDiscussionPostByIdCommand =
                new GetDiscussionPostByIdCommand(courseId, discussionPostId);
        final DiscussionPostsView discussionPostsView =
                discussionPostApplicationService.getDiscussionPostById(getDiscussionPostByIdCommand);
        return DiscussionPostResponseDto.from(discussionPostsView);
    }

}
