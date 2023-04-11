package com.epam.taskmanager.repository;

import com.epam.taskmanager.model.Task;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {

    Collection<Task> findByCategory(@NotNull String category);

}
