package com.stc.assignment.controller;

import com.stc.assignment.exception.FolderNotFoundException;
import com.stc.assignment.exception.PermissionDeniedException;
import com.stc.assignment.model.*;
import com.stc.assignment.service.FileService;
import com.stc.assignment.service.ItemService;
import com.stc.assignment.service.PermissionGroupService;
import com.stc.assignment.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.io.IOException;

@RestController
public class ItemController {
    @Autowired
    ItemService itemService;
    @Autowired
    PermissionGroupService permissionGroupService;
    @Autowired
    PermissionService permissionService;
    @Autowired
    FileService fileService;

    @PostMapping("/Spaces")
    public ResponseEntity<Item> createSpace(@RequestBody @Valid SpaceFolderRequest spaceRequest){
        Item item = new Item(ItemType.SPACE,spaceRequest.getName(),permissionGroupService.getGroupById(spaceRequest.getPermissionGroup()),null);
        itemService.createItem(item);
        return new ResponseEntity<Item>(HttpStatus.OK);
    }
    @PostMapping("/spaces/{spaceId}/folders")
    public ResponseEntity createFolder(@RequestBody @Valid SpaceFolderRequest folderRequest, @PathVariable Long spaceId)throws EntityNotFoundException {
        Item parent = itemService.findItemById(spaceId);
        if(!permissionService.havePermissionToEdit(folderRequest.getUserEmail(),parent.getPermissionGroup().getId())) throw new PermissionDeniedException();
        Item folderItem = new Item(ItemType.FOLDER,folderRequest.getName(),permissionGroupService.getGroupById(folderRequest.getPermissionGroup()),spaceId);
        itemService.createItem(folderItem );
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/spaces/{spaceId}/folders/{folderId}/files")
    public ResponseEntity<File> createFile(
            @RequestParam MultipartFile file,
            @RequestParam String name,
            @RequestParam String userEmail,
            @RequestParam Long permissionGroupId,
            @PathVariable Long spaceId,
            @PathVariable Long folderId) throws EntityNotFoundException, IOException {
        Item folderItem = itemService.findItemById(folderId);
        if(folderItem.getParentID()!=spaceId)throw new FolderNotFoundException();
        if(!permissionService.havePermissionToEdit(userEmail,folderItem.getPermissionGroup().getId()))throw new PermissionDeniedException();
        Item fileItem = new Item(ItemType.FILE,name,permissionGroupService.getGroupById(permissionGroupId),folderId);
        itemService.createItem(fileItem);
        File file1 = new File(file.getBytes(),fileItem);
        fileService.createFile(file1);
        return new ResponseEntity<File>(HttpStatus.OK);
    }
}
