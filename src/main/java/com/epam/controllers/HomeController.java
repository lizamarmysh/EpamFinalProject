package com.epam.controllers;

import com.epam.forms.StudentForm;
import com.epam.forms.TeacherForm;
import com.epam.modeles.*;
import com.epam.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private PersonalTaskService personalTaskService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private GroupService groupService;

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public String getForm(@RequestParam("role") String role, StudentForm studentForm, TeacherForm teacherForm, ModelMap modelMap, HttpSession session) {
        if (role.equalsIgnoreCase("teacher")) {
            Teacher teacher = teacherService.checkAuthorization(Teacher.from(teacherForm));
            if (teacher != null) {
                teacher.setStudents(teacherService.getAllStudents());
                teacher.setPersonalTasks(teacherService.getPersonalTasks(teacher));

                modelMap.addAttribute("tasks",teacherService.getAllTasks());
                modelMap.addAttribute("students", teacherService.getAllStudents());
                modelMap.addAttribute("subjects",subjectService.getAllSubjects());
                modelMap.addAttribute("groups",groupService.getAllGroups());

                modelMap.addAttribute("teacher", teacher);
                session.setAttribute("teacher", teacher);
                return "teacher_home";
            } else {
                return "error";
            }

        } else if (role.equalsIgnoreCase("student")) {
            Student student = studentService.checkAuthorization(Student.from(studentForm));
            if (student != null) {
                List<PersonalTask> personalTasks = new ArrayList<>();
                for (PersonalTask personalTask : studentService.getPersonalTasks(student)) {
                    Optional<PersonalTask> personalTaskOptional = personalTaskService.getPersonalTaskById(personalTask.getId());
                    if (personalTaskOptional.isPresent()) {
                        personalTasks.add(personalTaskOptional.get());
                    }
                }
                student.setPersonalTasks(personalTasks);
                modelMap.addAttribute("student", student);
                session.setAttribute("student", student);
                return "user_home";
            } else {
                return "error";
            }
        }
        return "error";
    }
}
