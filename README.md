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
| GET | localhost:8080/users/{id} | This request lists all users within a household ID. E.g. if {id} = 1 then this lists users in household ID=1. |
| POST| localhost:8080/users  | This request creates a new user. |
| PATCH | localhost:8080/users/{id}| This request updates user's information, e.g. task.|
|DELETE | localhost:8080/users/{id} | This request is to delete a user and their tasks assigned to them.|

**Household**

| Request | Endpoints | Description|
| :-------: | :-------:| :-------:|
| GET | localhost:8080/households | This request lists all households. |
| GET | localhost:8080/households/{id} | This request lists all information within a household ID.|
| POST| localhost:8080/households | This request adds a new household. |
| PATCH | localhost:8080/households/{id}| This request updates household.|

**Task**

| Request | Endpoints | Description|
| :-------: | :-------:| :-------:|
| GET | localhost:8080/tasks | This request lists all tasks. |
| GET | localhost:8080/tasks/{id} | This request lists all tasks by task ID including the category and deadline.|
| GET | localhost:8080/filter-by-household/{householdId} |This request lists tasks within a household ID.|
| POST| localhost:8080/tasks | This request creates a new task. |
| PATCH | localhost:8080/tasks/{id}| This request updates specific tasks.|
| GET | localhost:8080/filter-by-category| This request lists all tasks that fall under the same category. |
| PATCH | localhost:8080/assign-task-by-user/{taskId}| This request allows for a user to assign another user a task that needs to be completed (assigning users have to be in the same household and over the age of 18.|
| PATCH | localhost:8080/update-status/{id}| This request updates the status of the task that needs to be completed e.g. "*NOT_STARTED*", "*IN_PROGRESS*" or "*COMPLETED*"|
| GET | localhost:8080/tasks/filter-by-overdue-tasks | This request lists incomplete tasks that have passed the given deadline. |
| DELETE | localhost:8080/delete-task/{taskId} | This request deletes tasks; can only be deleted by users over the age of 18 and are in the same household of the task that is assigned in. |


 