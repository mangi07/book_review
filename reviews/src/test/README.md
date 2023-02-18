## Curl Tests

### Register New User:

`curl -X POST -H "Content-Type: application/json" -d '{"username": "Bob", "password": "pass1"}' localhost:3000/auth/register`

### Login New User:

`curl -X POST -H "Content-Type: application/json" -d '{"username": "Bob", "password": "pass1"}' localhost:3000/auth/login`

### Search Protected Route for Book:

Use the token returned upon login...

Example request 1:

`curl -vv -H "Content-Type: application/json" 
-H "Authorization: Bearer $token" 
localhost:3000/search/0201558025`

Example response 1:

```json
{
  "book": {
    "id": 2,
    "isbn": "0201558025",
    "author": "Ronald L. Graham",
    "title": "Concrete mathematics",
    "description": "Includes bibliographical references (p. 604-631) and index.",
    "publication_date": "1994",
    "cover_image": "https://covers.openlibrary.org/b/id/135182-M.jpg",
    "author_obj": {}
  },
  "inLibrary": false
}
```

Example request 2:

`curl -vv -H "Content-Type: application/json"
-H "Authorization: Bearer $token"
localhost:3000/search/9780132350884`

Example response 2:

```json
{
  "book": {
    "id": 1,
    "isbn": "9780132350884",
    "author": "Robert C. Martin",
    "title": "Clean Code",
    "description": null,
    "publication_date": "July 2008",
    "cover_image": "https://covers.openlibrary.org/b/id/8085499-M.jpg",
    "author_obj": {}
  },
  "inLibrary": true
}
```
### Add Book to Logged-In User's Library:

`curl -vv -H "Content-Type: application/json" 
-H "Authorization: Bearer $token" 
localhost:3000/add/0201558025`

### Review Book:

If this book, which should be in the user's library, 
is already reviewed and rated, that review and rating
will be replaced with the one in this request.

`curl -X POST -H "Content-Type: application/json" 
-H "Authorization: Bearer $token" 
-d '{"review":"It is an okay book.", "rating":3}' 
localhost:3000/review/0201558025`

### List Reviews By ISBN:

Example request:

`curl -H "Content-Type: application/json" 
-H "Authorization: Bearer $token" 
localhost:3000/list/reviews/9780132350884`

Example response, returning a list of reviews for the book 
under the given ISBN:

```json
{
  "bookReviews": [
    {
      "id": 1,
      "userId": 1,
      "bookId": 1,
      "review": "It is a wonderful book!",
      "rating": 5
    }
  ]
}
```
### See Logged-In User's Library

Example request:

`curl -H "Content-Type: application/json" 
-H "Authorization: Bearer $token" 
localhost:3000/library/Bill`

Example response:

```json
{
  "books": [
    {
        "id": 1,
        "isbn": "9780132350884",
        "author": "Robert C. Martin",
        "title": "Clean Code",
        "description": null,
        "publication_date": "July 2008",
        "cover_image": "https://covers.openlibrary.org/b/id/8085499-M.jpg",
        "author_obj": {}
    },
    {
        "id": 2,
        "isbn": "0201558025",
        "author": "Ronald L. Graham",
        "title": "Concrete mathematics",
        "description": "Includes bibliographical references (p. 604-631) and index.",
        "publication_date": "1994",
        "cover_image": "https://covers.openlibrary.org/b/id/135182-M.jpg",
        "author_obj": {}
    }
  ]
}
```








