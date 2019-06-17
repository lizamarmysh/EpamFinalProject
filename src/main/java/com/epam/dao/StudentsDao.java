package com.epam.dao;

import com.epam.modeles.PersonalTask;
import com.epam.modeles.Student;

import java.util.List;
import java.util.Optional;

public interface StudentsDao extends CrudDao<Student> {
    Optional<Student> findStudentById(Integer id);
    Optional<Student> findStudentByLogPass(String login, String password);
    Optional<Student> findStudentByLogin(String login);
}
