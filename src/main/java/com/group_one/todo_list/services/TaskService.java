package com.group_one.todo_list.services;

import com.group_one.todo_list.models.*;
import com.group_one.todo_list.repositories.HouseholdRepository;
import com.group_one.todo_list.repositories.TaskRepository;
import com.group_one.todo_list.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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


    // TODO: EXTENSION: ADD LOGIC TO ENSURE THAT THE USER IS IN THE HOUSEHOLD OF THE TASK. AND LOGIC TO ENSURE HOUSEHOLDID AND USERID ARE VALID
    public Task updateTask(long id, TaskDTO taskDTO) {
        Optional<Task> taskOptional = taskRepository.findById(id);

        if (taskOptional.isEmpty()) {
            return null;
        }
        Task taskToUpdate = taskOptional.get();

        if (taskDTO.getDescription() != null) {
            taskToUpdate.setDescription(taskDTO.getDescription());
        }

        if (taskDTO.getCategory() != null) {
            taskToUpdate.setCategory(taskDTO.getCategory());
        }

        // use separate path to do this
//        if (taskDTO.getStatus() != null) {
//            taskToUpdate.setStatus(taskDTO.getStatus());
//        }

        if (taskDTO.getDueDate() != null) {
            taskToUpdate.setDueDate(taskDTO.getDueDate());
        }

        if (taskDTO.getHouseholdId() != 0) {
            Optional<Household> householdOptional = householdService.getHouseholdById(taskDTO.getHouseholdId());

            if (householdOptional.isEmpty()) {
                return null;
            }

            // remove the user from the task since we are changing household.
            taskToUpdate.setUser(null);
            Household household = householdOptional.get();
            taskToUpdate.setHousehold(household);
        }
        return taskRepository.save(taskToUpdate);
    }


    public List<Task> getTaskByCategory(Category category) {
        return taskRepository.findByCategoryEquals(category);
    }


    public List<Task> checkDueDate(DueDatePerHouseholdDTO dueDatePerHouseholdDTO) {
        LocalDate currentDate = dueDatePerHouseholdDTO.getCurrentDate();
        long householdId = dueDatePerHouseholdDTO.getHouseholdId();
        Optional<Household> householdOptional = householdService.getHouseholdById(householdId);
        if (householdOptional.isEmpty()) {
            return null;
        }
        List<Task> tasksOverDuePerHousehold = taskRepository.findByDueDateLessThanAndHouseholdIdEquals(currentDate, householdId);
        List<Task> uncompletedOverDueTasks = new ArrayList<>();
        for (Task task : tasksOverDuePerHousehold){
            if (!(task.getStatus().equals(Status.COMPLETED))){
                uncompletedOverDueTasks.add(task);
            }
        }
        return uncompletedOverDueTasks;
    }

    public Task assignUserToTaskByUser(Long taskId, UserAssignToUserDTO userAssignToUserDTO){

        Optional<Task> assignedTaskOptional = taskRepository.findById(taskId);
        Optional<User> userReceivingTaskOptional = userRepository.findById(userAssignToUserDTO.getUserReceivingTaskId());
        Optional<User> userAssigningTaskOptional = userRepository.findById(userAssignToUserDTO.getAssigningUserId());

        if (assignedTaskOptional.isEmpty() || userReceivingTaskOptional.isEmpty() || userAssigningTaskOptional.isEmpty()) {
            return null;
        }

        Task assignedTask = assignedTaskOptional.get();
        User userReceivingTask = userReceivingTaskOptional.get();
        User userAssigningTask = userAssigningTaskOptional.get();


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
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        if (taskOptional.isEmpty()) {
            return null;
        }

        Task task = taskOptional.get();
        if(task.getUser() == null){
            return null;
        }
        if (task.getHousehold().getId() == task.getUser().getHousehold().getId()) {
            task.setStatus(taskDTO.getStatus());
            return taskRepository.save(task);
        }
        return null;
    }

    public String deleteTask (long taskId, Long userId) {
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        Optional<User> userOptional = userRepository.findById(userId);

        if (taskOptional.isEmpty() || userOptional.isEmpty()) {
            return "Invalid task Id or user Id";
        }

        Task task = taskOptional.get();
        User user = userOptional.get();


        if (user.getHousehold().getId() == task.getHousehold().getId() && user.getAge() >= 18) {
            taskRepository.deleteById(taskId);
            return "Task " + taskId + "ID deleted successfully.";
        }
        return "User does not have permission to delete";
    }

    public List<Task> getTasksByHousehold (long householdId) {
        return taskRepository.findByHouseholdIdEquals(householdId);
    }


}
