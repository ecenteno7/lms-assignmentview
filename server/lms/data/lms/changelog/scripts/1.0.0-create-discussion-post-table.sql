CREATE TABLE discussion_post (
    id VARCHAR(36) NOT NULL,
    class_id VARCHAR(16) NOT NULL,
    user_id VARCHAR(16) NOT NULL,
    content VARCHAR(500) NOT NULL,
    created_on TIMESTAMPZ NOT NULL
);