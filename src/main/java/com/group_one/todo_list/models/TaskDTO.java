package com.group_one.todo_list.models;

import java.time.LocalDate;

public class TaskDTO {

    private String description;
    private String category;
    private Status status;
    private LocalDate dueDate;
    private long householdId;
    private long userId;

    public TaskDTO( String description, String category, Status status, LocalDate dueDate, long householdId, long userId) {
        this.description = description;
        this.category = category;
        this.status = status;
        this.dueDate = dueDate;
        this.householdId = householdId;
        this.userId = userId;
    }

    public TaskDTO() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public long getHouseholdId() {
        return householdId;
    }

    public void setHouseholdId(long householdId) {
        this.householdId = householdId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
