package com.group_one.todo_list.controllers;

import com.group_one.todo_list.models.*;
import com.group_one.todo_list.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping //localhost:8080/tasks
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}") //localhost:8080/tasks/1
    public ResponseEntity<Task> getTaskById (@PathVariable Long id){
        Optional<Task> foundTask = taskService.getTaskById(id);
        if (foundTask.isPresent()){
            return new ResponseEntity<>(foundTask.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/filter-by-household/{householdId}")
    public ResponseEntity<List<Task>> getTasksByHousehold(@PathVariable long householdId){
        List<Task> tasksByHousehold = taskService.getTasksByHousehold(householdId);
        return new ResponseEntity<>(tasksByHousehold, HttpStatus.OK);
    }

    @PostMapping //localhost:8080/tasks
    public ResponseEntity<Task> createTask (@RequestBody TaskDTO taskDTO) {
        Task newTask = taskService.createTask(taskDTO);
        return new ResponseEntity<>(newTask, HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{id}") //localhost:8080/tasks/1
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        Optional<Task> foundTask = taskService.getTaskById(id);
        if (foundTask.isPresent()) {
            Task updatedTask = taskService.updateTask(id, taskDTO);
            if (updatedTask == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping(value = "/assign-task/{taskId}") // localhost:8080/tasks/assign-task/1 (in the body, just put a number, not {"userId": 2})
    public ResponseEntity<Task> assignUserToTask (@PathVariable Long taskId, @RequestBody Long userId) {
        Task updatedTask = taskService.assignUserToTask(taskId, userId);
        if(updatedTask == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @GetMapping(value = "/filter-by-category") // localhost:8080/tasks/filter-by-category?category=CLEANING
    public ResponseEntity<List<Task>> getAllByCategory(@RequestParam(required = false) Category category) {
        List<Task> tasks = taskService.getTaskByCategory(category);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }


    // for this path, taskId in the path, the users assigning and receiving the task are in the body
    @PatchMapping(value = "/assign-task-by-user/{taskId}") // localhost:8080/tasks/assign-task-by-user/1
    public ResponseEntity<Task> assignUserToTaskByUser(@PathVariable Long taskId, @RequestBody UserAssignToUserDTO userAssignToUserDTO){
        Task updatedTask = taskService.assignUserToTaskByUser(taskId, userAssignToUserDTO);
        if (updatedTask == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @PatchMapping(value = "/update-status/{id}") //  localhost:8080/tasks/update-status/1  // update task 1
    public ResponseEntity<Task> updateStatusOfTask(@PathVariable long id, @RequestBody TaskDTO taskDTO) {
        Task updatedTask = taskService.updateStatus(id, taskDTO);
        if (updatedTask == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);

    }

    @GetMapping(value = "/filter-by-overdue-tasks") // localhost:8080/tasks/filter-by-overdue-tasks
    public ResponseEntity<List<Task>> getAllByDueDate(@RequestBody DueDatePerHouseholdDTO dueDatePerHouseholdDTO) {
        List<Task> tasks = taskService.checkDueDate(dueDatePerHouseholdDTO);
        if (tasks == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    //  localhost:8080/tasks/delete-task/1                 the payload will just be 2 for userId 2
    @DeleteMapping(value = "/delete-task/{taskId}") // localhost:8080/delete-task/{taskId}
    public ResponseEntity<String> deleteTask(@PathVariable long taskId, @RequestBody Long userId) {
        String message = taskService.deleteTask(taskId, userId);

        if (message.equals("User does not have permission to delete")) {
            return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
        } else if (message.equals("Invalid task Id or user Id")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
