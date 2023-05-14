package com.stc.assignment.model;

import javax.validation.constraints.*;

public class SpaceFolderRequest {

    @NotEmpty
    private String name;
    @Email
    private String userEmail;
    @NotNull
    private Long permissionGroupId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Long getPermissionGroup() {
        return permissionGroupId;
    }

    public void setPermeissionGroup(Long permissionGroupId) {
        this.permissionGroupId = permissionGroupId;
    }

    public SpaceFolderRequest(String name, String userEmail, Long permissionGroupId) {
        this.name = name;
        this.userEmail = userEmail;
        this.permissionGroupId = permissionGroupId;
    }

}
