package org.lms.assignmentview.presentation.rest.dto.assignment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import org.lms.assignmentview.domain.assignment.Module;
import org.lms.assignmentview.domain.assignment.commands.CreateModuleCommand;
import org.lms.assignmentview.domain.user.User;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.time.OffsetDateTime;

@Builder
public record ModuleDto(

        @JsonProperty("moduleID")
        @Nullable String moduleId,

        @JsonProperty("tagID")
        @Nullable String tagId,

        @Nullable OffsetDateTime createdOn,

        @Nullable OffsetDateTime updatedOn,

        int moduleNumber,

        @NonNull String title,

        @NonNull String description
) {

    public static @NonNull ModuleDto from(@NonNull final Module module) {
        return ModuleDto.builder()
                .moduleId(module.getModuleId().id())
                .tagId(module.getTagId().id())
                .createdOn(module.getCreatedOn())
                .updatedOn(module.getUpdatedOn())
                .moduleNumber(module.getModuleNumber())
                .title(module.getTitle())
                .description(module.getDescription())
                .build();
    }

    public @NonNull CreateModuleCommand toCreateModuleCommand(@NonNull final User user) {
        return CreateModuleCommand.builder()
                .user(user)
                .moduleNumber(moduleNumber)
                .title(title)
                .description(description)
                .build();
    }

}
