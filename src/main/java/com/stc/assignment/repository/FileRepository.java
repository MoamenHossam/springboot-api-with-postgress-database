package com.stc.assignment.repository;

import com.stc.assignment.model.File;
import com.stc.assignment.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File,Long> {
    @Query(value="select * from file f\n" +
            "join item i on f.item_id = i.id where permission_group_id in (select group_id from permission where user_email=:userEmail);\n", nativeQuery=true)
    List<File> authorizedFileForUser(String userEmail);
}
