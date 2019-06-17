package com.epam.services;

import com.epam.dao.SubjectDao;
import com.epam.modeles.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    private SubjectDao subjectDao;

    public Optional<Subject> getSubjectById(int id){
        return subjectDao.findSubjectById(id);
    }

    public List<Subject> getAllSubjects(){
        return subjectDao.findAll();
    }
}
