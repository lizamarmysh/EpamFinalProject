package com.epam.controllers;

import com.epam.forms.TaskForm;
import com.epam.modeles.Group;
import com.epam.modeles.Subject;
import com.epam.modeles.Task;
import com.epam.modeles.Teacher;
import com.epam.services.GroupService;
import com.epam.services.SubjectService;
import com.epam.services.TaskService;
import com.epam.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class SearchTaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private GroupService groupService;

    @RequestMapping(value = "/searchTask", method = RequestMethod.GET)
    public String getSelectTask(@RequestParam("name") String name, @RequestParam("subjectId") Integer subjectId, ModelMap modelMap, HttpSession session) {
        if (session.getAttribute("teacher") != null) {
            Teacher teacher = (Teacher) session.getAttribute("teacher");
            List<Task> tasks = new ArrayList<>();
            if ((name == "") && (subjectId == null)) {
                for (Task task : teacherService.getAllTasks()) {
                    Optional<Task> taskOptional = taskService.getTaskById(task.getId());
                    if (taskOptional.isPresent()) {
                        tasks.add(taskOptional.get());
                    }
                }
            } else {
                for (Task task : taskService.getTasksByNameOrSubject(name, subjectId)) {
                    Optional<Task> taskOptional = taskService.getTaskById(task.getId());
                    if (taskOptional.isPresent()) {
                        tasks.add(taskOptional.get());
                    }
                }
            }

            modelMap.addAttribute("subjects", subjectService.getAllSubjects());
            modelMap.addAttribute("groups",groupService.getAllGroups());
            modelMap.addAttribute("tasksAfterSearch", tasks);
            modelMap.addAttribute("tasks",teacherService.getAllTasks());
            modelMap.addAttribute("students", teacherService.getAllStudents());
            modelMap.addAttribute("isChange", "responseToTaskSearch");


            return "teacher_home";
        }
        modelMap.addAttribute("errorSearch","true");
        return "error";
    }
}
