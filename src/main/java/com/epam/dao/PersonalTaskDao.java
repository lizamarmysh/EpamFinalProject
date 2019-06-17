package com.epam.dao;

import com.epam.modeles.PersonalTask;
import com.epam.modeles.Student;
import com.epam.modeles.Teacher;

import java.util.List;

public interface PersonalTaskDao extends CrudDao<PersonalTask> {
    List<PersonalTask> findPersonalTaskByStudent(Student student);
    List<PersonalTask> findPersonalTaskByTeacher(Teacher teacher);
}
