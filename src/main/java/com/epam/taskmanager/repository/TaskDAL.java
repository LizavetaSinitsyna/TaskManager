package com.epam.taskmanager.repository;

import com.epam.taskmanager.model.Subtask;
import com.epam.taskmanager.model.Task;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.Collection;

public interface TaskDAL {
    Collection<Task> findOverdue(@NotNull LocalDate date);

    Collection<Task> findByDescription(@NotNull String description);

    Collection<Subtask> findSubtasksByCategory(String category);

    Collection<Task> findSubtasksByName(String name);
}
