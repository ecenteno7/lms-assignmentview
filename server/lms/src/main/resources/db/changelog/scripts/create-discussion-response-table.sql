CREATE TABLE discussion_response (
    id uuid NOT NULL,
    discussion_post_id uuid NOT NULL,
    parent_response_id uuid NULL,
    author_id varchar(16) NOT NULL,
    class_id varchar(16) NOT NULL,
    created_on timestamptz NOT NULL,
    updated_on timestamptz NULL,
    content text NOT NULL,
    vote_count integer NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT discussion_post_fk FOREIGN KEY (discussion_post_id) REFERENCES discussion_post (id),
    CONSTRAINT parent_response_fk FOREIGN KEY (parent_response_id) REFERENCES discussion_response(id)
);