package com.group_one.todo_list.models;

public class UserAssignToUserDTO {

    private long taskId;
    private long userId;

    public UserAssignToUserDTO(long taskId, long userId) {
        this.taskId = taskId;
        this.userId = userId;
    }

    public UserAssignToUserDTO() {
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
