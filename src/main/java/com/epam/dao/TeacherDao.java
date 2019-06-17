package com.epam.dao;

import com.epam.modeles.Teacher;

import java.util.Optional;

public interface TeacherDao extends CrudDao<Teacher> {
    Optional<Teacher> findTeacherById(Integer id);
    Optional<Teacher> findTeacherByLogPass(String login, String password);
    Optional<Teacher> findTeacherByLogin(String login);
}
