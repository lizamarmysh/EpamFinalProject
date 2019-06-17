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
    public String getForm(@RequestParam("role") String role, StudentForm studentForm, TeacherForm teacherForm, ModelMap modelMap, HttpSession httpSession) {
        if (role.equalsIgnoreCase("teacher")) {
//            Teacher teacher = Teacher.from(teacherForm);
            Teacher teacher = teacherService.checkAuthorization(Teacher.from(teacherForm));
            if (teacher != null) {
//                List<Task> tasks = new ArrayList<>();
//                for (Task task : teacherService.getAllTasks()) {
//                    Optional<Task> taskOptional = taskService.getTaskById(task.getId());
//                    if (taskOptional.isPresent()) {
//                        tasks.add(taskOptional.get());
//                    }
//                }

//                List<Student> students = new ArrayList<>();
//                for (Student student : teacherService.getAllStudents()) {
//                    Optional<Student> studentOptional = studentService.getStudentById(student.getId());
//                    if (studentOptional.isPresent()) {
//                        students.add(studentOptional.get());
//                    }
//                }

//                List<Subject> subjects = new ArrayList<>();
//                for (Subject subject : subjectService.getAllSubjects()) {
//                    Optional<Subject> subjectOptional = subjectService.getSubjectById(subject.getId());
//                    if (subjectOptional.isPresent()) {
//                        subjects.add(subjectOptional.get());
//                    }
//                }

//                List<Group> groups = new ArrayList<>();
//                for (Group group : groupService.getAllGroups()) {
//                    Optional<Group> groupOptional = groupService.getGroupById(group.getId());
//                    if (groupOptional.isPresent()) {
//                        groups.add(groupOptional.get());
//                    }
//                }

//                List<PersonalTask> personalTasks = new ArrayList<>();
//                for (PersonalTask personalTask : teacherService.getPersonalTasks(teacher1)) {
//                    Optional<PersonalTask> personalTaskOptional = personalTaskService.getPersonalTaskById(personalTask.getId());
//                    if (personalTaskOptional.isPresent()) {
//                        personalTasks.add(personalTaskOptional.get());
//                    }
//                }
                teacher.setStudents(teacherService.getAllStudents());
                teacher.setPersonalTasks(teacherService.getPersonalTasks(teacher));

                modelMap.addAttribute("tasks",teacherService.getAllTasks());
                modelMap.addAttribute("students", teacherService.getAllStudents());
                modelMap.addAttribute("subjects",subjectService.getAllSubjects());
                modelMap.addAttribute("groups",groupService.getAllGroups());

                modelMap.addAttribute("teacher", teacher);
                httpSession.setAttribute("teacher", teacher);
                return "teacher_home";
            } else {
                return "error";
            }

        } else if (role.equalsIgnoreCase("student")) {
//            Student student = Student.from(studentForm);
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
                httpSession.setAttribute("student", student);
                return "user_home";
            } else {
                return "error";
            }
        }
        return "error";
    }
}
