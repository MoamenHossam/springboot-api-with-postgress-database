package com.stc.assignment.service;

import com.stc.assignment.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {
    @Autowired
    PermissionRepository permissionRepository;

    public Boolean havePermissionToEdit(String email,Long groupId){
        if(permissionRepository.haveEditAccess(email,groupId)!=null)return true;
        else return false;
    }
    public Boolean havePermissionToView(String email,Long groupId){
        if(permissionRepository.haveEditAccess(email,groupId)!=null)return true;
        else return false;
    }
}
