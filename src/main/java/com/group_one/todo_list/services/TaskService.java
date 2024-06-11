package com.group_one.todo_list.services;

import com.group_one.todo_list.models.Task;
import com.group_one.todo_list.models.TaskDTO;
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

    public Task updateTask(long id, TaskDTO taskDTO) {
        Task taskToUpdate = taskRepository.findById(id).get();

        if (taskDTO.getDescription() != null) {
            taskToUpdate.setDescription(taskDTO.getDescription());
        }

        if (taskDTO.getCategory() != null) {
            taskToUpdate.setCategory(taskDTO.getCategory());
        }

        if (taskDTO.getStatus() != null) {
            taskToUpdate.setStatus(taskDTO.getStatus());
        }

        if (taskDTO.getStatus() != null) {
            taskToUpdate.setCategory(taskDTO.getCategory());
        }

        if (taskDTO.getDueDate() != null) {
            taskToUpdate.setDueDate(taskDTO.getDueDate());
        }

//        TODO: We need to be able to get Household by ID
//        if (!(taskDTO.getHouseholdId() == 0)){
//            taskToUpdate.setHousehold();
//        }
//
//        TODO: We need to be able to get User by ID

        return taskRepository.save(taskToUpdate);
    }

    public Optional<Task> assignTaskToUser(long taskId, long userId) {
        Task assignedTask = taskRepository.findById(taskId).get();
        User user = userRepository.findById(userId).get();
        user.addTask(assignedTask);


    }

}
