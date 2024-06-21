CREATE TABLE discussion_post (
    id uuid NOT NULL,
    author_id varchar(16) NOT NULL,
    class_id varchar(16) NOT NULL,
    created_on timestamptz NOT NULL,
    updated_on timestamptz NULL,
    title text NOT NULL,
    content text NOT NULL,
    vote_count integer NOT NULL,
    PRIMARY KEY (id)
);