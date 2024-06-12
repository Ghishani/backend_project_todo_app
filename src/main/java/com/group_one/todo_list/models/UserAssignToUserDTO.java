package com.group_one.todo_list.models;

public class UserAssignToUserDTO {

    private Long assigningUserId;
    private Long userReceivingTaskId;

    public UserAssignToUserDTO( Long assigningUserId, Long userReceivingTaskId) {
        this.assigningUserId = assigningUserId;
        this.userReceivingTaskId = userReceivingTaskId;
    }

    public UserAssignToUserDTO() {
    }

    public Long getAssigningUserId() {
        return assigningUserId;
    }

    public void setAssigningUserId(Long assigningUserId) {
        this.assigningUserId = assigningUserId;
    }

    public Long getUserReceivingTaskId() {
        return userReceivingTaskId;
    }

    public void setUserReceivingTaskId(Long userReceivingTaskId) {
        this.userReceivingTaskId = userReceivingTaskId;
    }
}
