CREATE SEQUENCE library_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE book_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE author_seq START WITH 1 INCREMENT BY 1;


CREATE TABLE library
(
    id           NUMBER PRIMARY KEY,
    name         VARCHAR2(255) NOT NULL,
    update_count NUMBER,
    version      NUMBER,
    updated      TIMESTAMP,
    updated_by   VARCHAR2(255)
);


CREATE TABLE book
(
    id           NUMBER PRIMARY KEY,
    title        VARCHAR2(255) NOT NULL,
    update_count NUMBER,
    library_id   NUMBER REFERENCES Library (id),
    updated      TIMESTAMP,
    updated_by   VARCHAR2(255),
    version      NUMBER
);


CREATE TABLE author
(
    id           NUMBER PRIMARY KEY,
    name         VARCHAR2(255) NOT NULL,
    update_count NUMBER,
    updated      TIMESTAMP,
    updated_by   VARCHAR2(255),
    book_id      NUMBER REFERENCES Book (id),
    version      NUMBER
);
