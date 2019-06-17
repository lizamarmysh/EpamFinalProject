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

import java.util.Optional;

@Service
public class PersonalTaskService {

    @Autowired
    private StudentsDao studentsDao;

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private PersonalTaskDao personalTaskDao;

    public Optional<PersonalTask> getPersonalTaskById(int id){
        Optional<PersonalTask> personalTaskOptional = personalTaskDao.find(id);
        Optional<Student> studentOptional;
        Optional<Teacher> teacherOptional;
        Optional<Task> taskOptional;

        if(personalTaskOptional.isPresent()){
            PersonalTask personalTask = personalTaskOptional.get();
            studentOptional = studentsDao.findStudentById(personalTask.getStudentId());
            teacherOptional = teacherDao.findTeacherById(personalTask.getTeacherId());
            taskOptional = taskDao.findTaskById(personalTask.getTaskId());

            if(studentOptional.isPresent()){
                personalTask.setStudent(studentOptional.get());
            }
            if(teacherOptional.isPresent()){
                personalTask.setTeacher(teacherOptional.get());
            }
            if(taskOptional.isPresent()){
                personalTask.setTask(taskOptional.get());
            }
            return Optional.of(personalTask);
        }else{
            return Optional.empty();
        }
    }

    public void savePersonalTask(PersonalTask personalTask){
        personalTaskDao.save(personalTask);
    }
}
