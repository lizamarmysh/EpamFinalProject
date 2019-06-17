package com.epam.controllers;

import com.epam.dao.StudentsDao;
import com.epam.modeles.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.sql.DataSource;
import java.util.List;

@Controller
@RequestMapping("/login")
public class ControllerDao {
    @Autowired
    DataSource dataSource;

    @Autowired
    StudentsDao userDao;

    @RequestMapping(method = RequestMethod.POST)
    public String printRegisteredUser(@RequestParam String login,
                                      @RequestParam String password, ModelMap modelMap){
        List<Student> students = userDao.findAll();
        Student student = new Student(login, password);
        students.add(student);

        modelMap.addAttribute("users", students);
        return "login";
    }
}
