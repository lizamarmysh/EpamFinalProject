package com.epam.controllers;

import com.epam.forms.TaskForm;
import com.epam.modeles.*;
import com.epam.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.GenericServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CreateTaskController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private GroupService groupService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createTask(@RequestParam("taskName") String name, @RequestParam("subjectId") Integer subjectId, @RequestParam("taskText") String text, @ModelAttribute("TaskForm") TaskForm taskForm, ModelMap modelMap, HttpSession session) throws IOException {
        if (session.getAttribute("teacher") != null) {
            Teacher teacher = (Teacher) session.getAttribute("teacher");
            Task task = Task.from(taskForm);
            taskService.saveTask(task);

            modelMap.addAttribute("groups", groupService.getAllGroups());
            modelMap.addAttribute("students", teacherService.getAllStudents());
            modelMap.addAttribute("tasks", teacherService.getAllTasks());
            modelMap.addAttribute("subjects", subjectService.getAllSubjects());
            modelMap.addAttribute("errorCreate", "false");
            return "teacher_home";
        }
        modelMap.addAttribute("errorCreate", "true");
        return "error";
    }
}
