package com.group_one.todo_list.models;

import java.time.LocalDate;

public class DueDatePerHouseholdDTO {

    private long householdId;
    private LocalDate currentDate;


    public DueDatePerHouseholdDTO(long householdId, LocalDate currentDate) {
        this.householdId = householdId;
        this.currentDate = currentDate;
    }

    public DueDatePerHouseholdDTO() {
    }

    public long getHouseholdId() {
        return householdId;
    }

    public void setHouseholdId(long householdId) {
        this.householdId = householdId;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
    }
}
