package com.stc.assignment.controller;

import com.stc.assignment.model.File;
import com.stc.assignment.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class FileController {
    @Autowired
    FileRepository fileRepository;

    @QueryMapping
    Iterable<File> files(){
        return fileRepository.findAll();
    }

    @QueryMapping
    Iterable<File> authorizedFiles(@Argument String userEmail){
        return fileRepository.authorizedFileForUser(userEmail);
    }
}
