package com.epam.controllers;

import com.epam.modeles.Student;
import com.epam.modeles.Teacher;
import com.epam.services.GroupService;
import com.epam.services.StudentService;
import com.epam.services.SubjectService;
import com.epam.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class SearchStudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private GroupService groupService;

    @RequestMapping(value = "/searchStudent", method = RequestMethod.GET)
    public String getSelectTask(@RequestParam("name") String name, @RequestParam("surname") String surname, @RequestParam("groupId") Integer groupId, ModelMap modelMap, HttpSession session) {
        if (session.getAttribute("teacher") != null) {
            Teacher teacher = (Teacher) session.getAttribute("teacher");
            List<Student> students = new ArrayList<>();
            if ((name == "") && (surname == "") && (groupId == null)) {
                for (Student student: teacherService.getAllStudents()){
                    Optional<Student> optionalStudent = studentService.getStudentById(student.getId());
                    if (optionalStudent.isPresent()){
                        students.add(optionalStudent.get());
                    }
                }
            } else {
                for (Student student:studentService.getStudentsByNameOrSurnameOrGroupId(name,surname,groupId)){
                    Optional<Student> optionalStudent = studentService.getStudentById(student.getId());
                    if (optionalStudent.isPresent()){
                        students.add(optionalStudent.get());
                    }
                }
            }

            modelMap.addAttribute("groups",groupService.getAllGroups());
            modelMap.addAttribute("subjects", subjectService.getAllSubjects());
            modelMap.addAttribute("studentsAfterSearch", students);
            modelMap.addAttribute("tasks",teacherService.getAllTasks());
            modelMap.addAttribute("students", teacherService.getAllStudents());
            modelMap.addAttribute("isChange", "responseToStudentSearch");

            return "teacher_home";
        }
        modelMap.addAttribute("errorSearch","true");
        return "error";
    }
}
