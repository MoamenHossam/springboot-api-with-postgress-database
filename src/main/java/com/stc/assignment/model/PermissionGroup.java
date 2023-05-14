package com.stc.assignment.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class PermissionGroup {
    @Id
    @SequenceGenerator(
            name = "permission_group_sequence",
            sequenceName = "permission_group_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "permission_group_sequence"
    )
    private Long id;

    private String groupName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public PermissionGroup(String groupName) {
        this.groupName = groupName;
    }

    public PermissionGroup() {
    }
}
