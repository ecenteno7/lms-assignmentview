CREATE TABLE class (
    class_id varchar(16) NOT NULL,
    course_prefix varchar NOT NULL,
    course_number int NOT NULL,
    course_name varchar NOT NULL,
    PRIMARY KEY (class_id)
);

ALTER TABLE discussion_post
    ADD CONSTRAINT class_fk FOREIGN KEY (class_id) REFERENCES class (class_id);
ALTER TABLE discussion_response
    ADD CONSTRAINT class_fk FOREIGN KEY (class_id) REFERENCES class (class_id);

CREATE TABLE discussion_user (
    user_id varchar(16) NOT NULL,
    username varchar(50) NOT NULL,
    first_name varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL,
    class_id varchar(16) NOT NULL,
    role varchar(10) NOT NULL,
    PRIMARY KEY (user_id),
    CONSTRAINT class_fk FOREIGN KEY (class_id) REFERENCES class (class_id)
);

ALTER TABLE discussion_post
    ADD CONSTRAINT author_fk FOREIGN KEY (author_id) REFERENCES discussion_user (user_id);
ALTER TABLE discussion_response
    ADD CONSTRAINT author_fk FOREIGN KEY (author_id) REFERENCES discussion_user (user_id);