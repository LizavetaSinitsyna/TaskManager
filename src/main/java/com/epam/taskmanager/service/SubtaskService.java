package com.epam.taskmanager.service;

import com.epam.taskmanager.model.Subtask;

import java.util.Collection;

public interface SubtaskService {
    Collection<Subtask> findByCategory(String category);
}
