CREATE TABLE discussion_post (
    id uuid,
    author_id varchar(16),
    class_id varchar(16),
    created_on timestamptz,
    content text
);