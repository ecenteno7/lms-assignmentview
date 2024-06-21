package org.lms.assignmentview.infrastructure;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lms.assignmentview.DatabaseConfig;
import org.lms.assignmentview.domain.DiscussionPostRepository;
import org.lms.assignmentview.domain.discussion.DiscussionPost;
import org.lms.assignmentview.domain.discussion.DiscussionPostId;
import org.lms.assignmentview.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest(excludeAutoConfiguration = DataSourceAutoConfiguration.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DatabaseConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DiscussionPostRepositoryImplTest {

    @Autowired
    private DiscussionPostRepository discussionPostRepository;

    @Test
    void save() {
        final DiscussionPost discussionPost = DiscussionPost.builder()
                .id(DiscussionPostId.createId())
                .author(new User("userId", "classId"))
                .createdOn(OffsetDateTime.now())
                .content("Discussion post content")
                .voteCount(4)
                .build();
        final DiscussionPost savedDiscussionPost = discussionPostRepository.save(discussionPost);
        assertEquals(discussionPost, savedDiscussionPost);
    }

}
