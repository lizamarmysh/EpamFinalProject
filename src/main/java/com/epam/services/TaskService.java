package com.epam.services;

import com.epam.dao.SubjectDao;
import com.epam.dao.TaskDao;
import com.epam.modeles.Subject;
import com.epam.modeles.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private SubjectDao subjectDao;

    @Autowired
    private TaskDao taskDao;

    public Optional<Task> getTaskById(int id){
        Optional<Task> taskOptional = taskDao.findTaskById(id);
        Optional<Subject> subjectOptional;

        if(taskOptional.isPresent()){
            Task task = taskOptional.get();
            subjectOptional = subjectDao.findSubjectById(task.getSubjectId());

            if(subjectOptional.isPresent()){
                task.setSubject(subjectOptional.get());
            }
            return Optional.of(task);
        }else{
            return Optional.empty();
        }
    }

    public void saveTask(Task task){
        taskDao.save(task);
    }

    public List<Task> getTasksByNameOrSubject(String name, Integer subjectId){
        return taskDao.findTaskByNameOrSubject(name, subjectId);
    }
}
