ALTER TABLE class
    ADD CONSTRAINT unique_prefix_and_number UNIQUE (course_prefix, course_number);