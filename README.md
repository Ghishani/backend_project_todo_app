# HomeChamp To-Do List
#### To do or not to do, that is the task!

### Intro

This API provides a to-do list for occupants of a household. Tasks are created for a household which can be assigned to a user within the household to be completed. Each member of a household has a preference of category in which various tasks fall under; users will be able to be assigned tasks according to their preferred categories. Categories include *shopping, cleaning, cooking*. 

### Tech Stack

- Java
- Spring Boot
- Postgres

### How to run this API locally
- install Postgres (via homebrew or www.postgresapp.com)
- create a database `createdb todo_list` in Terminal
- install Java
- clone this project to your computer and run `main()` in `TodoListApplication`
- you may wish to view endpoints in Postman
- you may wish to view database in Postico

### API Design

*erd and uml*

### Endpoints

**User**

| Request | Endpoints | Description|
| :-------: | :-------:| :-------:|
| GET | localhost:8080/users | This request lists all users. |
| GET | localhost:8080/users/1 | This request lists all users within a household ID. E.g. this URL lists users in household ID=1. |
| POST| localhost:8080/users  | This request creates a new user. |
| PATCH | localhost:8080/users/{id}| This request updates user's information, e.g. task.|
|DELETE | localhost:8080/users/1 | This request is to delete a user and their tasks assigned to them e.g. this URL will delete user ID=1.|

**Households**

| Request | Endpoints | Description|
| :-------: | :-------:| :-------:|
| GET | localhost:8080/households | This request lists all households. |
| GET | localhost:8080/households/1 | This request lists all information within a household ID. E.g. this URL lists users in household ID=1. |
| POST| localhost:8080/households | This request adds a new household. |
| PATCH | localhost:8080/households/{id}| This request updates household.|

