package org.lms.assignmentview;

import org.lms.assignmentview.domain.discussion.DiscussionPostRepository;
import org.lms.assignmentview.infrastructure.DiscussionPostRepositoryImpl;
import org.lms.assignmentview.infrastructure.jpa.repository.JpaDiscussionPostRepository;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;

@Configuration
@AutoConfigurationPackage
public class DatabaseConfig {

    @Bean
    @NonNull
    DiscussionPostRepository discussionPostRepository(
            @NonNull final JpaDiscussionPostRepository jpaDiscussionPostRepository
    ) {
        return new DiscussionPostRepositoryImpl(jpaDiscussionPostRepository);
    }

}
