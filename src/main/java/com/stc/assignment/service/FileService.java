package com.stc.assignment.service;

import com.stc.assignment.model.File;
import com.stc.assignment.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {
    @Autowired
    FileRepository fileRepository;

    public List<File> getAllFiles(){
        return fileRepository.findAll();
    }
    public void createFile(File file){
        fileRepository.save(file);
    }
    public File getFileById(Long fileId){
        return fileRepository.getOne(fileId);
    }
}
