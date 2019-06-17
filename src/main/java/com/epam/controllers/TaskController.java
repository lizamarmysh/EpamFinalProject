package com.epam.controllers;

import com.epam.modeles.*;
import com.epam.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private PersonalTaskService personalTaskService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/task", method = RequestMethod.GET)
    public String getTask(@RequestParam("id") Integer id, ModelMap modelMap, HttpSession session) throws IOException {
        if (session.getAttribute("student") != null) {
            if (id != null) {
                Student student = (Student) session.getAttribute("student");
                Optional<Task> optionalTask = taskService.getTaskById(id);
                if (optionalTask.isPresent()) {
                    Task task = optionalTask.get();
                    Optional<PersonalTask> optionalPersonalTask = personalTaskService.getPersonalTaskById(id);
                    if (optionalPersonalTask.isPresent()) {
                        modelMap.addAttribute("activeTask",task);
                        modelMap.addAttribute("teacher", optionalPersonalTask.get().getTeacher());
                    }

                    return "user_home";
                }
            }
        } else if (session.getAttribute("teacher") != null) {
            Teacher teacher = (Teacher) session.getAttribute("teacher");
            Optional<Task> optionalTask = taskService.getTaskById(id);
            if (optionalTask.isPresent()) {
                Task task = optionalTask.get();

                Optional<PersonalTask> optionalPersonalTask = personalTaskService.getPersonalTaskById(id);
                if (optionalPersonalTask.isPresent()) {

//                    List<Task> tasks = new ArrayList<>();
//                    for (Task task1 : teacherService.getAllTasks()) {
//                        Optional<Task> taskOptional = taskService.getTaskById(task1.getId());
//                        if (taskOptional.isPresent()) {
//                            tasks.add(taskOptional.get());
//                        }
//                    }

//                    List<Subject> subjects = new ArrayList<>();
//                    for (Subject subject : subjectService.getAllSubjects()) {
//                        Optional<Subject> subjectOptional = subjectService.getSubjectById(subject.getId());
//                        if (subjectOptional.isPresent()) {
//                            subjects.add(subjectOptional.get());
//                        }
//                    }

//                    List<Student> students = new ArrayList<>();
//                    for (Student student : teacherService.getAllStudents()) {
//                        Optional<Student> studentOptional = studentService.getStudentById(student.getId());
//                        if (studentOptional.isPresent()) {
//                            students.add(studentOptional.get());
//                        }
//                    }

                    modelMap.addAttribute("subject", task.getSubject());
                    modelMap.addAttribute("subjects",subjectService.getAllSubjects());
                    modelMap.addAttribute("activeTask", task);
                    modelMap.addAttribute("tasks", teacherService.getAllTasks());
                    modelMap.addAttribute("student", optionalPersonalTask.get().getStudent());
                    modelMap.addAttribute("teacher", optionalPersonalTask.get().getTeacher());
                    modelMap.addAttribute("students", teacherService.getAllStudents());
                    modelMap.addAttribute("isChange","allTask");
                }

                return "teacher_home";
            }
        }
        return null;
    }
}
