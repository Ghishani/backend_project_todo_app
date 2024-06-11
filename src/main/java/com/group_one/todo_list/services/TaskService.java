package com.group_one.todo_list.services;

import com.group_one.todo_list.models.Task;
import com.group_one.todo_list.models.User;
import com.group_one.todo_list.repositories.TaskRepository;
import com.group_one.todo_list.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(long id) {
        return taskRepository.findById(id);
    }

//    TODO: May need a derived query
//    public Optional<List<Task>> getTaskById(String category) {
//        return taskRepository.
//    }

    public Task

    public Optional<Task> assignTaskToUser(long taskId, long userId) {
        Task assignedTask = taskRepository.findById(taskId).get();
        User user = userRepository.findById(userId).get();
        user.addTask(assignedTask);


    }

}
