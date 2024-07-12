CREATE TABLE assignment (
    id uuid NOT NULL,
    tag_id uuid NOT NULL,
    creator varchar(16) NOT NULL,
    class_id varchar(16) NOT NULL,
    created_on timestamptz NOT NULL,
    updated_on timestamptz NULL,
    title text NOT NULL,
    description text NOT NULL,
    due_date timestamptz NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT tag_fk FOREIGN KEY (tag_id) REFERENCES tag (id),
    CONSTRAINT creator_fk FOREIGN KEY (creator) REFERENCES discussion_user (id),
    CONSTRAINT class_fk FOREIGN KEY (class_id) REFERENCES class (id)
);

CREATE TABLE module (
    id uuid NOT NULL,
    assignment_id uuid NOT NULL,
    tag_id uuid NOT NULL,
    creator varchar(16) NOT NULL,
    class_id varchar(16) NOT NULL,
    created_on timestamptz NOT NULL,
    updated_on timestamptz NULL,
    module_number int NOT NULL,
    title text NOT NULL,
    description text NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT assignment_fk FOREIGN KEY (assignment_id) REFERENCES assignment (id),
    CONSTRAINT tag_fk FOREIGN KEY (tag_id) REFERENCES tag (id),
    CONSTRAINT creator_fk FOREIGN KEY (creator) REFERENCES discussion_user (id),
    CONSTRAINT class_fk FOREIGN KEY (class_id) REFERENCES class (id)
)