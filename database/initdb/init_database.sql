CREATE TABLE IF NOT EXISTS subject (
        id serial NOT NULL,
        name character varying(50),
        create_date timestamp with time zone,
        update_date timestamp with time zone,
        PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS book (
        id serial NOT NULL,
        title character varying(50),
        edition_number character varying(50),
        create_date timestamp with time zone,
        update_date timestamp with time zone,
        PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS author(
        id serial NOT NULL,
        name character varying(50),
        create_date timestamp with time zone,
        update_date timestamp with time zone,
        PRIMARY KEY (id)
);

ALTER TABLE book ADD COLUMN subject_id integer;

CREATE TABLE IF NOT EXISTS book_author (
        id serial NOT NULL,
        book_id integer not null,
        author_id integer not null,
        create_date timestamp with time zone,
        update_date timestamp with time zone,
        PRIMARY KEY (id)
);

ALTER TABLE book ADD COLUMN status character varying(30);
ALTER TABLE book ADD COLUMN available_to_borrow boolean;
ALTER TABLE book ADD COLUMN last_loan_date timestamp with time zone;
ALTER TABLE book ADD COLUMN publish_year timestamp with time zone;

CREATE TABLE IF NOT EXISTS ticket (
       id serial NOT NULL,
       book_id integer not null,
       start_loan_date timestamp with time zone,
       expired_loan_date timestamp with time zone,
       create_date timestamp with time zone,
       update_date timestamp with time zone,
       PRIMARY KEY (id)
);
