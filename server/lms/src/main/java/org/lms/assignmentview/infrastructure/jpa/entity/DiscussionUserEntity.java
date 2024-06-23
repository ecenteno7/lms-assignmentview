package org.lms.assignmentview.infrastructure.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import org.lms.assignmentview.domain.course.CourseId;
import org.lms.assignmentview.domain.user.Role;
import org.lms.assignmentview.domain.user.User;
import org.lms.assignmentview.domain.user.UserDetails;
import org.lms.assignmentview.domain.user.UserId;

@Getter
@NoArgsConstructor
@SuperBuilder
@Entity(name = "discussion_user")
public class DiscussionUserEntity {

    @Id
    private String id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "class_id")
    private String classId;

    @Column(name = "role")
    private String role;

    public static @NonNull DiscussionUserEntity from(@NonNull final UserDetails userDetails) {
        return DiscussionUserEntity.builder()
                .id(userDetails.getUser().userId().id())
                .username(userDetails.getUsername())
                .password(userDetails.getPassword())
                .firstName(userDetails.getFirstName())
                .lastName(userDetails.getLastName())
                .classId(userDetails.getUser().classId().id())
                .role(userDetails.getRole().name())
                .build();
    }

    public @NonNull UserDetails toDomain() {
        return UserDetails.builder()
                .user(new User(new UserId(id), new CourseId(classId)))
                .username(username)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .role(Role.valueOf(role))
                .build();
    }

}
