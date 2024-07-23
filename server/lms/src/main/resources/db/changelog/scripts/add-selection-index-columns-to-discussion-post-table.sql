ALTER TABLE discussion_post
    ADD selection_reference_id uuid NULL,
    ADD selection_start_index integer NULL,
    ADD selection_end_index integer NULL;