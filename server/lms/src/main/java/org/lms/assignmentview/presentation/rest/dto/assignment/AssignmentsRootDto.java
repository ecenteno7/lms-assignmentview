package org.lms.assignmentview.presentation.rest.dto.assignment;

import lombok.NonNull;
import org.lms.assignmentview.application.AssignmentView;

import java.util.List;

public record AssignmentsRootDto(
        @NonNull List<AssignmentDto> assignments
) {

    public static @NonNull AssignmentsRootDto from(@NonNull final List<AssignmentView> assignmentViews) {
        return new AssignmentsRootDto(assignmentViews.stream()
                .map(AssignmentDto::from)
                .toList());
    }

}
