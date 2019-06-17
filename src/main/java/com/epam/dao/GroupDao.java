package com.epam.dao;

import com.epam.modeles.Group;

import java.util.Optional;

public interface GroupDao extends CrudDao<Group> {
    Optional<Group> findGroupById(Integer id);
}
