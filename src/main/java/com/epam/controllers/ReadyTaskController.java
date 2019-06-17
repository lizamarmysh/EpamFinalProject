package com.epam.controllers;

import com.epam.forms.PersonalTaskForm;
import com.epam.forms.StudentForm;
import com.epam.forms.TeacherForm;
import com.epam.modeles.PersonalTask;
import com.epam.modeles.Student;
import com.epam.modeles.Teacher;
import com.epam.services.PersonalTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class ReadyTaskController {

    @Autowired
    private PersonalTaskService personalTaskService;

    @RequestMapping(value = "/ready", method = RequestMethod.GET)
    public String getForm(@ModelAttribute("PersonalTaskForm") PersonalTaskForm personalTaskForm, ModelMap modelMap, HttpSession session) {
        if (session.getAttribute("student") != null) {


            Student student = (Student) session.getAttribute("student");
            PersonalTask personalTask = PersonalTask.fromAllParams(personalTaskForm);
            modelMap.addAttribute("student", student);
            modelMap.addAttribute("errorReady", "false");
            modelMap.addAttribute("isHide", "true");
            personalTaskService.updatePersonalTask(personalTask);
            return "user_home";
        }
        return "error";
    }
}
