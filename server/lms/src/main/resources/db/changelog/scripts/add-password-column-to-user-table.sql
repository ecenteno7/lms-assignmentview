ALTER TABLE discussion_user
    ADD password VARCHAR(50) NULL;

UPDATE discussion_user
    SET password = 'password'
    WHERE password is null;

ALTER TABLE discussion_user
    ALTER COLUMN password SET NOT NULL;