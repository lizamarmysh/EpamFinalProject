package com.epam.services;

import com.epam.dao.GroupDao;
import com.epam.modeles.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    @Autowired
    private GroupDao groupDao;

    public Optional<Group> getGroupById(int id){
        return groupDao.findGroupById(id);
    }

    public List<Group> getAllGroups(){
        return groupDao.findAll();
    }
}
