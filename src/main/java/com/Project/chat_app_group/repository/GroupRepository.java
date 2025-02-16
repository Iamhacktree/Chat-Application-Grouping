package com.Project.chat_app_group.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.Project.chat_app_group.model.Group;

@Repository
public interface GroupRepository extends MongoRepository<Group, String>{
	boolean existsByName(String name);
}
