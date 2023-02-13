## Curl Tests

### Register New User:

`curl -X POST -H "Content-Type: application/json" -d '{"username": "Bob", "password": "pass1"}' localhost:3000/auth/register`

### Login New User:

`curl -X POST -H "Content-Type: application/json" -d '{"username": "Bob", "password": "pass1"}' localhost:3000/auth/login`

### Search Protected Route for Book:

Use the token returned upon login...

`curl -vv -H "Content-Type: application/json" 
-H "Authorization: Bearer $token" 
localhost:3000/search/0201558025`

`curl -vv -H "Content-Type: application/json"
-H "Authorization: Bearer $token"
localhost:3000/search/9780132350884`

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