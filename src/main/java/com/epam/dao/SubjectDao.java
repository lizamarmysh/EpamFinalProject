package com.epam.dao;

import com.epam.modeles.Subject;

import java.util.Optional;

public interface SubjectDao extends CrudDao<Subject> {
    Optional<Subject> findSubjectById(Integer id);
}
