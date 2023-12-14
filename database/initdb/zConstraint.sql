ALTER TABLE ONLY book
    ADD CONSTRAINT subject_id_fk
    FOREIGN KEY (subject_id)
    REFERENCES subject(id);

ALTER TABLE ONLY book_author
    ADD CONSTRAINT book_author_id_fk
    FOREIGN KEY (author_id)
    REFERENCES author(id);

ALTER TABLE ONLY book_author
    ADD CONSTRAINT author_book_id_fk
    FOREIGN KEY (book_id)
    REFERENCES book(id);

ALTER TABLE ONLY ticket
    ADD CONSTRAINT ticket_book_id_fk
    FOREIGN KEY (book_id)
    REFERENCES book(id);
