package com.group_one.todo_list.services;

import com.group_one.todo_list.models.*;
import com.group_one.todo_list.repositories.HouseholdRepository;
import com.group_one.todo_list.repositories.TaskRepository;
import com.group_one.todo_list.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    HouseholdService householdService;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(long id) {
        return taskRepository.findById(id);
    }

    public Task createTask(TaskDTO taskDTO) {
        Optional<Household> householdOptional = householdService.getHouseholdById(taskDTO.getHouseholdId());

        if (householdOptional.isEmpty()) {
            return null;
        }

        Household household = householdOptional.get();
        Task newTask = new Task(
                taskDTO.getDescription(),
                taskDTO.getCategory(),
                taskDTO.getDueDate(),
                household);

        return taskRepository.save(newTask);
    }



//    TODO: May need a derived query
//    public Optional<List<Task>> getTaskById(String category) {
//        return taskRepository.
//    }

    // TODO: EXTENSION: ADD LOGIC TO ENSURE THAT THE USER IS IN THE HOUSEHOLD OF THE TASK. AND LOGIC TO ENSURE HOUSEHOLDID AND USERID ARE VALID
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

        if (taskDTO.getDueDate() != null) {
            taskToUpdate.setDueDate(taskDTO.getDueDate());
        }

        if (taskDTO.getHouseholdId() != 0) {
            Household household = householdService.getHouseholdById(taskDTO.getHouseholdId()).get();
            taskToUpdate.setHousehold(household);
        }
        return taskRepository.save(taskToUpdate);
    }


    public Task assignUserToTask(Long taskId, Long userId) {
        Task assignedTask = taskRepository.findById(taskId).get();
        User user = userRepository.findById(userId).get();
        user.addTask(assignedTask);
        assignedTask.setUser(user);
        return taskRepository.save(assignedTask);
    }

    public List<Task> getTaskByCategory(Category category) {
        return taskRepository.findByCategoryEquals(category);
    }

    public void deleteTask(long taskId){
        taskRepository.deleteById(taskId);
    }

    public Task assignUserToTaskByUser(Long userAssigningId, UserAssignToUserDTO userAssignToUserDTO){
        Task assignedTask = taskRepository.findById(userAssignToUserDTO.getTaskId()).get();
        User user = userRepository.findById(userAssignToUserDTO.getUserId()).get();
        user.addTask(assignedTask);
        assignedTask.setUser(user);
        return taskRepository.save(assignedTask);
    }

}
