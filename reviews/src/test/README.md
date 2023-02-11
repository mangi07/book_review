## Curl Tests

### Register New User:

`curl -X POST -H "Content-Type: application/json" -d '{"username": "Bob", "password": "pass1"}' localhost:3000/register`

### Login New User:

`curl -X POST -H "Content-Type: application/json" -d '{"username": "Bob", "password": "pass1"}' localhost:3000/login`

### Search Protected Route for Book:

Use the token returned upon login...

`curl -vv -H "Content-Type: application/json" \
-H "Authorization: Bearer $token" \
localhost:3000/search/0201558025
