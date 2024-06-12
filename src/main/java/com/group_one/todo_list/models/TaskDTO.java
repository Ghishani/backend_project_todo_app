package com.group_one.todo_list.models;

import java.time.LocalDate;

public class TaskDTO {

    private String description;
    private Category category;
    private Status status;
    private LocalDate dueDate;
    private long householdId;


    public TaskDTO( String description, Category category, Status status, LocalDate dueDate, long householdId) {
        this.description = description;
        this.category = category;
        this.status = status;
        this.dueDate = dueDate;
        this.householdId = householdId;
    }

    public TaskDTO() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
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

}
