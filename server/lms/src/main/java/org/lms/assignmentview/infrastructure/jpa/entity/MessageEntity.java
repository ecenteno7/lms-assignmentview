package org.lms.assignmentview.infrastructure.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import org.lms.assignmentview.domain.chatter.Message;
import org.lms.assignmentview.domain.chatter.MessageId;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.user.User;
import org.lms.assignmentview.domain.user.UserId;
import org.springframework.lang.Nullable;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
@SuperBuilder
@Entity(name = "message")
public class MessageEntity {

    @Id
    @NonNull
    private UUID id;

    @Column(name = "author_id", updatable = false)
    @NonNull
    private String authorId;

    @Column(name = "class_id", updatable = false)
    @NonNull
    private String classId;

    @Column(name = "created_on", updatable = false)
    @NonNull
    private OffsetDateTime createdOn;

    @Column(name = "updated_on")
    @Nullable
    private OffsetDateTime updatedOn;

    @Column(name = "content")
    @NonNull
    private String content;

    public static @NonNull MessageEntity from(@NonNull final Message message) {
        return MessageEntity.builder()
                .id(UUID.fromString(message.getMessageId().id()))
                .authorId(message.getAuthor().userId().id())
                .classId(message.getAuthor().classId().id())
                .createdOn(message.getMessageDateTime())
                .content(message.getContent())
                .build();
    }

    public @NonNull Message toDomain() {
        return Message.builder()
                .messageId(new MessageId(id.toString()))
                .author(new User(new UserId(authorId), new CourseId(classId)))
                .content(content)
                .messageDateTime(createdOn)
                .build();
    }

}
