package org.lms.assignmentview.presentation.rest.dto.assignment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.NonNull;
import org.lms.assignmentview.application.AssignmentView;
import org.lms.assignmentview.domain.assignment.Assignment;
import org.lms.assignmentview.domain.assignment.commands.CreateAssignmentCommand;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.user.User;
import org.lms.assignmentview.domain.user.UserId;
import org.lms.assignmentview.presentation.rest.dto.discussion.post.DiscussionPostDto;
import org.springframework.lang.Nullable;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Builder
public record AssignmentDto(
        @JsonProperty("assignmentID")
        @Nullable String assignmentId,

        @JsonProperty("tagID")
        @Nullable String tagID,

        @JsonProperty("authorID")
        @NonNull String authorId,

        @Nullable OffsetDateTime createdOn,

        @Nullable OffsetDateTime updatedOn,

        @NonNull String title,

        @NonNull String description,

        @NonNull OffsetDateTime dueDate,

        @Nullable List<ModuleDto> modules,

        @Nullable List<DiscussionPostDto> insights
) {

    public static @NonNull AssignmentDto from(@NonNull final AssignmentView assignmentView) {
        final Assignment assignment = assignmentView.assignment();
        AssignmentDtoBuilder assignmentDtoBuilder = AssignmentDto.builder()
                .assignmentId(assignment.getAssignmentId().id())
                .tagID(assignment.getTagId().id())
                .authorId(assignment.getCreator().userId().id())
                .createdOn(assignment.getCreatedOn())
                .updatedOn(assignment.getUpdatedOn())
                .title(assignment.getTitle())
                .description(assignment.getDescription())
                .dueDate(assignment.getDueDate());
        if (assignmentView.displayModules()) {
            assignmentDtoBuilder = assignmentDtoBuilder
                    .modules(assignment.getModules().stream()
                            .map(ModuleDto::from)
                            .toList())
                    .insights(assignmentView.insights().stream()
                            .map(DiscussionPostDto::from)
                            .toList());
        }
        return assignmentDtoBuilder.build();
    }

    public @NonNull CreateAssignmentCommand toCreateAssignmentCommand(@NonNull final CourseId courseId) {
        final User user = new User(new UserId(authorId), courseId);
        return CreateAssignmentCommand.builder()
                .creator(user)
                .title(title)
                .description(description)
                .dueDate(dueDate)
                .createModuleCommands(Optional.ofNullable(modules).stream()
                        .flatMap(List::stream)
                        .map(moduleDto -> moduleDto.toCreateModuleCommand(user))
                        .toList())
                .build();
    }

}
