CREATE TABLE message
(
    id         uuid        NOT NULL,
    author_id  varchar(16) NOT NULL,
    class_id   varchar(16) NOT NULL,
    created_on timestamptz NOT NULL,
    updated_on timestamptz NULL,
    content    text        NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT author_fk FOREIGN KEY (author_id) REFERENCES discussion_user (id),
    CONSTRAINT class_fk FOREIGN KEY (class_id) REFERENCES class (id)
);