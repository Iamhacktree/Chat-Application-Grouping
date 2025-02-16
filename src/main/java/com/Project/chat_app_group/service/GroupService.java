package com.Project.chat_app_group.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Project.chat_app_group.model.Group;
import com.Project.chat_app_group.repository.GroupRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    /**
     * Get all group names from the database.
     * @return List of group names
     */
    public List<String> getAllGroups() {
        List<Group> groups = groupRepository.findAll();
        List<String> groupNames = new ArrayList<>();

        for (Group group : groups) {
            groupNames.add(group.getName());
        }

        return groupNames;
    }


    /**
     * Create a new group if it doesn't already exist.
     * @param groupName Name of the new group
     * @return Success or error message
     */
    public String createGroup(String groupName) {
        if (groupRepository.existsByName(groupName)) {
            return "Group already exists!";
        }

        Group group = new Group();
        group.setName(groupName);
        groupRepository.save(group);

        return "Group created successfully!";
    }

    /**
     * Check if a group exists by name.
     * @param groupName Name of the group
     * @return Boolean indicating existence
     */
    public boolean groupExists(String groupName) {
        return groupRepository.existsByName(groupName);
    }
}

