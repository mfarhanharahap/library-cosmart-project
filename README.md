# Library-Cosmart



## Description
The application was built on Spring Boot (as backend service) and PostgreSQL (as database service) with Docker Compose.

## Local Setup
1. Clone Repo
```
cd existing_repo
git remote add origin https://github.com/mfarhanharahap/library-cosmart-project.git
```
2. To build and run use:
```
docker compose up -- build -d
```
2. To build and run use:
```
docker compose up -- build -d
```
3. Using the application:
- Get All Books by Subject (example: love)
```
curl --location 'http://localhost:8080/library/book/get-books?subjectName=love'
```
- Book Schedule (with params bookId)
```
curl --location --request POST 'http://localhost:8080/library/ticket/schedule?bookId=1'
```
4. To stop the application
```
docker compose down
```