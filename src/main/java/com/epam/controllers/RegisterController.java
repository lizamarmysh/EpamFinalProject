package com.epam.controllers;

import com.epam.forms.RegistrationForm;
import com.epam.forms.StudentForm;
import com.epam.forms.TeacherForm;
import com.epam.modeles.Group;
import com.epam.modeles.Student;
import com.epam.modeles.Teacher;
import com.epam.services.GroupService;
import com.epam.services.StudentService;
import com.epam.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class RegisterController {

    @Autowired
    GroupService groupService;

    @Autowired
    StudentService studentService;

    @Autowired
    TeacherService teacherService;


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String showRegister(@RequestParam("role") String role, @ModelAttribute("RegistrationForm") RegistrationForm registrationForm, ModelMap modelMap) {
        if (role.equalsIgnoreCase("student")) {
            Student student = Student.fromAllParams(registrationForm);
            modelMap.addAttribute("groups", groupService.getAllGroups());
            modelMap.addAttribute("isHide","true");

            if (studentService.saveStudent(student)){
                modelMap.addAttribute("error","false");
                return "registration";
            } else {
                modelMap.addAttribute("error","true");
                return "registration";
            }
        } else if (role.equalsIgnoreCase("teacher")) {
            Teacher teacher = Teacher.fromAllParams(registrationForm);
            modelMap.addAttribute("teacher", new Teacher());
            modelMap.addAttribute("isHide","true");

            if (teacherService.saveTeacher(teacher)) {
                modelMap.addAttribute("error","false");
                return "registration";
            } else {
                modelMap.addAttribute("error","true");
                return "registration";
            }
        }
        return null;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String show(StudentForm studentForm, ModelMap modelMap) {
        modelMap.addAttribute("groups", groupService.getAllGroups());
        return "registration";
    }

}
