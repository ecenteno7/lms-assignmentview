package org.lms.assignmentview.presentation.websocket.dto;

import lombok.NonNull;
import org.lms.assignmentview.application.MessageView;

import java.util.List;

public record MessagesDto(
        @NonNull List<MessageDto> messages
) {

    public static @NonNull MessagesDto from(@NonNull List<MessageView> messageViews) {
        return new MessagesDto(messageViews.stream()
                .map(MessageDto::from)
                .toList());
    }

}
