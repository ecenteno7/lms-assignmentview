ALTER TABLE discussion_user
    ADD CONSTRAINT unique_username UNIQUE (username);