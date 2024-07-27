package org.lms.assignmentview.presentation.websocket.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.NonNull;
import org.lms.assignmentview.application.MessageView;
import org.lms.assignmentview.domain.chatter.Message;
import org.lms.assignmentview.domain.chatter.commands.CreateMessageCommand;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.user.User;
import org.lms.assignmentview.domain.user.UserDetails;
import org.lms.assignmentview.domain.user.UserId;
import org.springframework.lang.Nullable;

import java.time.OffsetDateTime;

@Builder
public record MessageDto(
        @JsonProperty("authorID")
        @NonNull String authorId,

        @NonNull String content,

        @Nullable String firstName,

        @Nullable String lastName,

        @Nullable OffsetDateTime messageDateTime
) {

    public static @NonNull MessageDto from(@NonNull final MessageView messageView) {
        final Message message = messageView.message();
        final UserDetails userDetails = messageView.authorDetails();
        return MessageDto.builder()
                .authorId(message.getAuthor().userId().id())
                .content(message.getContent())
                .firstName(userDetails.getFirstName())
                .lastName(userDetails.getLastName())
                .messageDateTime(message.getMessageDateTime())
                .build();
    }

    public @NonNull CreateMessageCommand toCreateMessageCommand(@NonNull final CourseId courseId) {
        final User author = new User(new UserId(authorId), courseId);
        return new CreateMessageCommand(author, content);
    }

}
