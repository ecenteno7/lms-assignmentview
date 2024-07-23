ALTER TABLE tag
    ADD parent_tag_id uuid NULL,
    ADD CONSTRAINT parent_tag_fk FOREIGN KEY (parent_tag_id) REFERENCES tag (id);