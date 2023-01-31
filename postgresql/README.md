# Connecting to db

## Prerequisites

The following must be installed on localhost:
* psql client (version 14.6 running on host)
* docker compose (version 2.15.1)

In the container: 
* server 15.1 (Debian 15.1-1.pgdg110+1)

Note: Install versions other than the ones used here may or may not work.


## Running

Run the following command in this directory which should contain file 'docker-compose.yml':
```bash
docker compose up -d
```
Test your ability to connect to the docker service:
```bash
psql -h localhost -U postgres -p 5001 -d postgres
```
Note that you must have the PostgreSQL client (psql) installed on your machine to connect this way.

Or you may enter the container:
```bash
docker exec -it postgresql-database-1 bash
```
Then log in to PostgreSQL with user postgres and password postgres.

Test post request for user registration:
```bash
curl -X POST -H "Content-Type: application/json" -d '{"username": "Bob", "password": "pass1"}' localhost:3000/register
```
This will connect to the Tomcat server (port 3000) which then communicates with the PostgreSQL server (port 5001) running in the docker container.

