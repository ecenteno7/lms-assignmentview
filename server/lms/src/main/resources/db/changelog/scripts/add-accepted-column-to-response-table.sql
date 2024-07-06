ALTER TABLE discussion_response
    ADD accepted boolean NULL;

UPDATE discussion_response
    SET accepted = false
    WHERE accepted is null;

ALTER TABLE discussion_response
    ALTER COLUMN accepted SET NOT NULL;