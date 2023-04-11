package com.epam.taskmanager.repository.impl;

import com.epam.taskmanager.model.Subtask;
import com.epam.taskmanager.model.Task;
import com.epam.taskmanager.repository.TaskDAL;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Repository
public class TaskDALImpl implements TaskDAL {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public TaskDALImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Collection<Task> findOverdue(@NotNull LocalDate date) {
        Query query = new Query();
        query.addCriteria(Criteria.where("deadline").lt(date));
        return mongoTemplate.find(query, Task.class);
    }

    @Override
    public Collection<Task> findByDescription(@NotNull String description) {
        Query query = new Query();
        query.addCriteria(Criteria.where("description").regex(description));
        return mongoTemplate.find(query, Task.class);
    }

    @Override
    public Collection<Subtask> findSubtasksByCategory(String category) {
        Query query = new Query();
        query.addCriteria(Criteria.where("category").is(category));
        Collection<Task> tasks = mongoTemplate.find(query, Task.class);
        Collection<Subtask> subtasks = new ArrayList<>();
        for (Task task : tasks) {
            subtasks.addAll(task.getSubtasks());
        }
        return subtasks;
    }

    @Override
    public Collection<Task> findSubtasksByName(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("subtasks.name").regex(name));
        return mongoTemplate.find(query, Task.class);
    }
}
