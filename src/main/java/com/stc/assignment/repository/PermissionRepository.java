package com.stc.assignment.repository;

import com.stc.assignment.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission,Long> {
    @Query(value="select * from permission p where p.user_email= :userEmail and p.group_id= :group_id and permission_level = 'EDIT'", nativeQuery=true)
    Permission haveEditAccess(String userEmail,Long group_id);
}
