package com.epam.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class ExitController {

    @RequestMapping(value = "/exit", method = RequestMethod.GET)
    public String exit(HttpSession session){
        if (session.getAttribute("teacher") != null) {
            session.removeAttribute("teacher");
        } else if (session.getAttribute("student") != null){
            session.removeAttribute("student");
        }
        return "login";
    }
}
