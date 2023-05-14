package com.stc.assignment.repository;

import com.stc.assignment.model.PermissionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermessionGroupRepository extends JpaRepository<PermissionGroup,Long> {
}
