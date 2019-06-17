package com.epam.dao;

import com.epam.modeles.Task;

import java.util.Optional;

public interface TaskDao extends CrudDao<Task> {
    Optional<Task> findTaskById(Integer id);
}
