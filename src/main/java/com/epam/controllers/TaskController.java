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
    private GroupService groupService;

    @RequestMapping(value = "/task", method = RequestMethod.GET)
    public String getTask(@RequestParam("id") Integer id, ModelMap modelMap, HttpSession session) throws IOException {
        if (session.getAttribute("student") != null) {
            Student student = (Student) session.getAttribute("student");
            Optional<PersonalTask> personalTaskOptional = personalTaskService.getPersonalTaskById(id);

            if (personalTaskOptional.isPresent()) {
                Task task = personalTaskOptional.get().getTask();
                modelMap.addAttribute("activeTask", task);
                modelMap.addAttribute("subject", subjectService.getSubjectById(task.getSubjectId()).get());
                modelMap.addAttribute("personalTask",personalTaskOptional.get());
                modelMap.addAttribute("statusTask", personalTaskOptional.get().getStatus());
                modelMap.addAttribute("student", student);
                modelMap.addAttribute("teacher", personalTaskOptional.get().getTeacher());
            }
            return "user_home";

        } else if (session.getAttribute("teacher") != null) {
            Teacher teacher = (Teacher) session.getAttribute("teacher");
            Optional<Task> optionalTask = taskService.getTaskById(id);
            if (optionalTask.isPresent()) {
                Task task = optionalTask.get();

                Optional<PersonalTask> optionalPersonalTask = personalTaskService.getPersonalTaskById(id);
                if (optionalPersonalTask.isPresent()) {

                    modelMap.addAttribute("subject", task.getSubject());
                    modelMap.addAttribute("subjects", subjectService.getAllSubjects());
                    modelMap.addAttribute("activeTask", task);
                    modelMap.addAttribute("groups",groupService.getAllGroups());
                    modelMap.addAttribute("tasks", teacherService.getAllTasks());
                    modelMap.addAttribute("student", optionalPersonalTask.get().getStudent());
                    modelMap.addAttribute("teacher", teacher);
                    modelMap.addAttribute("students", teacherService.getAllStudents());
                    modelMap.addAttribute("isChange", "allTask");
                }
                return "teacher_home";
            }
        }
        modelMap.addAttribute("errorViewTask", "true");
        return "error";
    }
}
