package com.stc.assignment.service;

import com.stc.assignment.model.PermissionGroup;
import com.stc.assignment.repository.PermessionGroupRepository;
import com.stc.assignment.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionGroupService {

    @Autowired
    PermessionGroupRepository permessionGroupRepository;


    public  PermissionGroup getGroupById(Long id){
        return permessionGroupRepository.getOne(id);
    }

}
