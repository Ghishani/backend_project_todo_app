package com.group_one.todo_list.models;

public class UserAssignToUserDTO {

    private long assigningUserId;
    private long userReceivingTaskId;

    public UserAssignToUserDTO(long assigningUserId, long userReceivingTaskId) {
        this.assigningUserId = assigningUserId;
        this.userReceivingTaskId = userReceivingTaskId;
    }

    public UserAssignToUserDTO() {
    }

    public long getAssigningUserId() {
        return assigningUserId;
    }

    public void setAssigningUserId(long assigningUserId) {
        this.assigningUserId = assigningUserId;
    }

    public long getUserReceivingTaskId() {
        return userReceivingTaskId;
    }

    public void setUserReceivingTaskId(long userReceivingTaskId) {
        this.userReceivingTaskId = userReceivingTaskId;
    }
}
