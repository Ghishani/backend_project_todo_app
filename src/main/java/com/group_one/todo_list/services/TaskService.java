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

// redudant as we have a way to add a user to a task via another user
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

    public Task assignUserToTaskByUser(Long taskId, UserAssignToUserDTO userAssignToUserDTO){

        Task assignedTask = taskRepository.findById(taskId).get();
        User userReceivingTask = userRepository.findById(userAssignToUserDTO.getUserReceivingTaskId()).get();
        User userAssigningTask = userRepository.findById(userAssignToUserDTO.getAssigningUserId()).get();

        if ((userAssigningTask.getAge() >= 18) &&
                (userAssigningTask.getHousehold().getId() == userReceivingTask.getHousehold().getId()) &&
                (assignedTask.getHousehold().getId() == userAssigningTask.getHousehold().getId())){
            userReceivingTask.addTask(assignedTask);
            assignedTask.setUser(userReceivingTask);
            return taskRepository.save(assignedTask);
        }  else {
            return null;
        }

    }

    public Task updateStatus (long taskId, TaskDTO taskDTO) {
        // the taskId is the id of the task you want to update
        // the taskDTO contains the status you want to update to
        Task task = taskRepository.findById(taskId).get();
        if(task.getUser() == null){
            return null;
        }
        if (task.getHousehold().getId() == task.getUser().getHousehold().getId()) {
            task.setStatus(taskDTO.getStatus());
            return taskRepository.save(task);
        }
        return null;
    }

    // We need a way of checking a user is assigned to a task before the task is completed


}
