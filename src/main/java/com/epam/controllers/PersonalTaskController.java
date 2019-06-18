package com.epam.controllers;

import com.epam.forms.PersonalTaskForm;
import com.epam.modeles.*;
import com.epam.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PersonalTaskController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private PersonalTaskService personalTaskService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private GroupService groupService;

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public String sendPersonalTask(@RequestParam("studentId") Integer studentId, @RequestParam("taskId") Integer taskId, @RequestParam("teacherId") Integer teacherId, @RequestParam("status") String status, @ModelAttribute("PersonalTaskForm") PersonalTaskForm personalTaskForm, ModelMap modelMap, HttpSession session) throws IOException {
        if (session.getAttribute("teacher") != null) {
            Teacher teacher = (Teacher) session.getAttribute("teacher");
            PersonalTask personalTask = PersonalTask.from(personalTaskForm);

            Optional<Student> optionalStudent = studentService.getStudentById(studentId);
            Optional<Task> optionalTask = taskService.getTaskById(taskId);

            if ((optionalStudent.isPresent()) && (optionalTask.isPresent())) {
                personalTaskService.savePersonalTask(personalTask);

                modelMap.addAttribute("groups", groupService.getAllGroups());
                modelMap.addAttribute("subjects", subjectService.getAllSubjects());
                modelMap.addAttribute("students", teacherService.getAllStudents());
                modelMap.addAttribute("tasks", teacherService.getAllTasks());
                modelMap.addAttribute("isChange", "sendTask");
                modelMap.addAttribute("errorSend", "false");
                return "teacher_home";
            }
        }
        modelMap.addAttribute("errorSend", "true");
        return "error";
    }
}
