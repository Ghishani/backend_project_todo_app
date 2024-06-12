package com.group_one.todo_list.controllers;

import com.group_one.todo_list.models.*;
import com.group_one.todo_list.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping(value = "/assign-task/{taskId}") // localhost:8080/tasks/1 (in the body, just put a number, not {"userId": 2})
    public ResponseEntity<Task> assignUserToTask (@PathVariable Long taskId, @RequestBody Long userId) {
        Task updatedTask = taskService.assignUserToTask(taskId, userId);
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
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

}
