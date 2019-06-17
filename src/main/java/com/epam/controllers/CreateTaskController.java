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
    private StudentService studentService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private GroupService groupService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createTask(@RequestParam("taskName") String name, @RequestParam("subjectId") Integer subjectId, @RequestParam("taskText") String text, @ModelAttribute("TaskForm") TaskForm taskForm, ModelMap modelMap, HttpSession session) throws IOException {
        if (session.getAttribute("teacher") != null) {
            Teacher teacher = (Teacher) session.getAttribute("teacher");
            Task task = Task.from(taskForm);

//            List<Task> tasks = new ArrayList<>();
//            for (Task task1 : teacherService.getAllTasks()) {
//                Optional<Task> taskOptional = taskService.getTaskById(task1.getId());
//                if (taskOptional.isPresent()) {
//                    tasks.add(taskOptional.get());
//                }
//            }

//            List<Group> groups = new ArrayList<>();
//            for (Group group : groupService.getAllGroups()) {
//                Optional<Group> groupOptional = groupService.getGroupById(group.getId());
//                if (groupOptional.isPresent()) {
//                    groups.add(groupOptional.get());
//                }
//            }

//            List<Student> students = new ArrayList<>();
//            for (Student student1 : teacherService.getAllStudents()) {
//                Optional<Student> studentOptional = studentService.getStudentById(student1.getId());
//                if (studentOptional.isPresent()) {
//                    students.add(studentOptional.get());
//                }
//            }

//            List<Subject> subjects = new ArrayList<>();
//            for (Subject subject : subjectService.getAllSubjects()) {
//                Optional<Subject> subjectOptional = subjectService.getSubjectById(subject.getId());
//                if (subjectOptional.isPresent()) {
//                    subjects.add(subjectOptional.get());
//                }
//            }

            taskService.saveTask(task);

            modelMap.addAttribute("groups", groupService.getAllGroups());
            modelMap.addAttribute("students", teacherService.getAllStudents());
            modelMap.addAttribute("tasks", teacherService.getAllTasks());
            modelMap.addAttribute("subjects", subjectService.getAllSubjects());
//            modelMap.addAttribute("isChange", "createTask");
            modelMap.addAttribute("errorCreate", "false");
            return "teacher_home";

        }
        return "error";
    }
}
