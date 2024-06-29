CREATE TABLE tag
(
    id       uuid        NOT NULL,
    class_id varchar(16) NOT NULL,
    name     varchar(50) NOT NULL,
    color    varchar(7)  NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT class_fk FOREIGN KEY (class_id) REFERENCES class (id)
);

CREATE TABLE post_tags
(
    discussion_post_id uuid NOT NULL,
    tag_id             uuid NOT NULL,
    PRIMARY KEY (discussion_post_id, tag_id),
    CONSTRAINT discussion_post_fk FOREIGN KEY (discussion_post_id) REFERENCES discussion_post (id),
    CONSTRAINT tag_fk FOREIGN KEY (tag_id) REFERENCES tag (id)
);