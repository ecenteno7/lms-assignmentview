package org.lms.assignmentview.domain.chatter;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import org.lms.assignmentview.domain.chatter.commands.CreateMessageCommand;
import org.lms.assignmentview.domain.user.User;

import java.time.OffsetDateTime;

@Value
@Builder
public class Message {

    @NonNull
    private final MessageId messageId;

    @NonNull
    private final User author;

    @NonNull
    private final String content;

    @NonNull
    private final OffsetDateTime messageDateTime;

    public static @NonNull Message from(@NonNull final CreateMessageCommand createMessageCommand) {
        return Message.builder()
                .messageId(MessageId.generateId())
                .author(createMessageCommand.author())
                .content(createMessageCommand.content())
                .messageDateTime(OffsetDateTime.now())
                .build();
    }

}
