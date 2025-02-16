package com.Project.chat_app_group.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Project.chat_app_group.model.Group;
import com.Project.chat_app_group.service.GroupService;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping
    public List<String> getGroups() {
        return groupService.getAllGroups();
    }

    @PostMapping
    public ResponseEntity<?> createGroup(@RequestBody Group group) {
        String response = groupService.createGroup(group.getName());
        
        if (response.equals("Group already exists!")) {
            return ResponseEntity.badRequest().body(response);
        }
        
        return ResponseEntity.ok(response);
    }
}

