-- CREATE USER postgres WITH PASSWORD 'postgres'; -- this has already been specified in docker-compose.yml
-- CREATE DATABASE reviews;
-- GRANT ALL PRIVILEGES ON DATABASE reviews TO postgres;

CREATE TABLE "author" (
  "id" INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  "name" VARCHAR,
  "bio" TEXT
);

CREATE TABLE "book" (
  "id" INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  "title" VARCHAR,
  "author" VARCHAR,
  "description" VARCHAR,
  "publication_date" DATE,
  "cover_image" TEXT, -- URL of the cover image of the book
  "author_id" INT NOT NULL, 
  CONSTRAINT fk_author FOREIGN KEY("author_id") REFERENCES "author" ("id") -- one author can have many books (one-to-many)
);

CREATE TABLE "user" (
  "id" INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  "name" VARCHAR,
  "password" VARCHAR
);

-- many-to-many: A user can have many books and a book can have many users.
CREATE TABLE "user_book" (
  -- "id" INT NOT NULL PRIMARY KEY,
  "id" INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  "user_id" INT  NOT NULL,
  "book_id" INT REFERENCES "book" ("id") NOT NULL,
  "review" TEXT NOT NULL, -- user's book review
  CONSTRAINT fk_user FOREIGN KEY("user_id") REFERENCES "user" ("id"),
  CONSTRAINT fk_book FOREIGN KEY("book_id") REFERENCES "book" ("id")
);

