package org.lms.assignmentview.domain.chatter;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.lms.assignmentview.domain.chatter.commands.CreateMessageCommand;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ChatterService {

    // todo: save the message in db
    public @NonNull Message createMessage(@NonNull final CreateMessageCommand createMessageCommand) {
        return Message.from(createMessageCommand);
    }

}
