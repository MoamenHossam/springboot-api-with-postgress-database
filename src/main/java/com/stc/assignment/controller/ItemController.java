package com.stc.assignment.controller;

import com.stc.assignment.exception.FolderNotFoundException;
import com.stc.assignment.exception.PermissionDeniedException;
import com.stc.assignment.model.*;
import com.stc.assignment.service.FileService;
import com.stc.assignment.service.ItemService;
import com.stc.assignment.service.PermissionGroupService;
import com.stc.assignment.service.PermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@RequestMapping("/")
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
    public ResponseEntity createFolder(@RequestBody  @Valid SpaceFolderRequest folderRequest, @PathVariable Long spaceId)throws EntityNotFoundException {
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
    @GetMapping("/files/{fileId}/download/{userEmail}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long fileId,@PathVariable String userEmail) {

        File file = fileService.getFileById(fileId);
        if(!permissionService.havePermissionToEdit(userEmail,file.getItem().getPermissionGroup().getId())) throw new PermissionDeniedException();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.attachment().filename(file.getItem().getName()).build());
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return new ResponseEntity<>(file.getFile(), headers, HttpStatus.OK);
    }
}
