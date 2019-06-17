package com.epam.services;

import com.epam.dao.GroupDao;
import com.epam.dao.PersonalTaskDao;
import com.epam.dao.StudentsDao;
import com.epam.modeles.Group;
import com.epam.modeles.PersonalTask;
import com.epam.modeles.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentsDao studentsDao;

    @Autowired
    private PersonalTaskDao personalTaskDao;

    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupDao groupDao;

    public Student checkAuthorization(Student student) {
        Optional<Student> optionalStudent = studentsDao.findStudentByLogPass(student.getLogin(), student.getPassword());
        Student currentStudent = optionalStudent.get();
        Group currentGroup;
        Optional<Group> group = groupService.getGroupById(currentStudent.getGroupId());
        if (group.isPresent()) {
            currentGroup = group.get();
            currentStudent.setGroup(group.get());
        }
        return currentStudent;
    }

    public List<PersonalTask> getPersonalTasks(Student student) {
        return personalTaskDao.findPersonalTaskByStudent(student);
    }

    public Optional<Student> getStudentById(int id){
        Optional<Student> studentOptional = studentsDao.findStudentById(id);
        Optional<Group> groupOptional;

        if(studentOptional.isPresent()){
            Student student = studentOptional.get();
            groupOptional = groupDao.findGroupById(student.getGroupId());

            if (groupOptional.isPresent()){
                student.setGroup(groupOptional.get());
            }
            return Optional.of(student);
        }else{
            return Optional.empty();
        }
    }

    public boolean saveStudent(Student student){
        if(isStudentExists(student)){
            return false;
        }
        studentsDao.save(student);
        return true;
    }

    private boolean isStudentExists(Student student){
        Optional<Student> optionalStudent = studentsDao.findStudentByLogin(student.getLogin());
        if (optionalStudent.isPresent()){
            return true;
        }
        return false;
    }

}
