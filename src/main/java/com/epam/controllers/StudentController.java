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
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private PersonalTaskService personalTaskService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private GroupService groupService;

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public String getStudent(@RequestParam("id") Integer id, ModelMap modelMap, HttpSession session) throws IOException {
        if (session.getAttribute("teacher") != null) {
            Teacher teacher = (Teacher) session.getAttribute("teacher");
            Optional<Student> optionalStudent = studentService.getStudentById(id);
            if (optionalStudent.isPresent()) {
                Student student = optionalStudent.get();

                List<PersonalTask> personalTasks = new ArrayList<>();
                for (PersonalTask personalTask : studentService.getPersonalTasks(student)) {
                    Optional<PersonalTask> personalTaskOptional = personalTaskService.getPersonalTaskById(personalTask.getId());
                    if (personalTaskOptional.isPresent()) {
                        personalTasks.add(personalTaskOptional.get());
                    }
                }

                modelMap.addAttribute("groups", groupService.getAllGroups());
                modelMap.addAttribute("subjects",subjectService.getAllSubjects());
                modelMap.addAttribute("personalTasks", personalTasks);
                modelMap.addAttribute("activeStudent", student);
                modelMap.addAttribute("students", teacherService.getAllStudents());
                modelMap.addAttribute("tasks", teacherService.getAllTasks());
                modelMap.addAttribute("isChange", "allStudent");
                return "teacher_home";
            }
        }
        modelMap.addAttribute("errorViewStudent", "true");
        return "error";
    }
}
