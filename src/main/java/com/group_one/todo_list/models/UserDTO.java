package com.group_one.todo_list.models;

public class UserDTO {
     private String name;
     private Category preference;
     private long householdId;

    public UserDTO(String name, Category preference, long householdId) {
        this.name = name;
        this.preference = preference;
        this.householdId = householdId;
    }

    public UserDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getPreference() {
        return preference;
    }

    public void setPreference(Category preference) {
        this.preference = preference;
    }

    public long getHouseholdId() {
        return householdId;
    }

    public void setHouseholdId(long householdId) {
        this.householdId = householdId;
    }
}
