package com.epam.services;

import com.epam.dao.PersonalTaskDao;
import com.epam.dao.StudentsDao;
import com.epam.dao.TaskDao;
import com.epam.dao.TeacherDao;
import com.epam.modeles.PersonalTask;
import com.epam.modeles.Student;
import com.epam.modeles.Task;
import com.epam.modeles.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private StudentsDao studentsDao;

    @Autowired
    private PersonalTaskDao personalTaskDao;

    public Teacher checkAuthorization(Teacher teacher) {
        Optional<Teacher> optionalTeacher = teacherDao.findTeacherByLogPass(teacher.getLogin(), teacher.getPassword());
        Teacher currentTeacher = optionalTeacher.get();

        return currentTeacher;
    }

    public List<Task> getAllTasks(){
        return taskDao.findAll();
    }

    public List<Student> getAllStudents(){
        return studentsDao.findAll();
    }

    public boolean saveTeacher(Teacher teacher){
        if(isTeacherExists(teacher)){
            return false;
        }
        teacherDao.save(teacher);
        return true;
    }

    private boolean isTeacherExists(Teacher teacher){
        Optional<Teacher> optionalTeacher = teacherDao.findTeacherByLogin(teacher.getLogin());
        if (optionalTeacher.isPresent()){
            return true;
        }
        return false;
    }

    public List<PersonalTask> getPersonalTasks(Teacher teacher){
        return personalTaskDao.findPersonalTaskByTeacher(teacher);
    }
}
