CREATE TABLE "book" (
  "id" int,
  "tite" varchar,
  "author" varchar,
  "description" varchar,
  "publication_date" date,
  "cover_image" text,
  "author_id" int,
  PRIMARY KEY ("id")
);

CREATE TABLE "author" (
  "id" int,
  "full_name" varchar,
  "bio" text,
  PRIMARY KEY ("id")
);

CREATE TABLE "user" (
  "id" int,
  "full_name" varchar,
  "password" varchar,
  PRIMARY KEY ("id")
);

ALTER TABLE "book" ADD FOREIGN KEY ("author_id") REFERENCES "author" ("id");

CREATE TABLE "book_user" (
  "book_id" int,
  "user_id" int,
  PRIMARY KEY ("book_id", "user_id")
);

ALTER TABLE "book_user" ADD FOREIGN KEY ("book_id") REFERENCES "book" ("id");

ALTER TABLE "book_user" ADD FOREIGN KEY ("user_id") REFERENCES "user" ("id");

