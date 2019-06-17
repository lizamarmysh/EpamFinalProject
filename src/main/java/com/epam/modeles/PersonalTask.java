package com.epam.modeles;

import com.epam.forms.PersonalTaskForm;
import lombok.*;

@Data
@AllArgsConstructor
@Builder
@ToString(exclude = "student")
public class PersonalTask {
    private Integer id;
    private Integer studentId;
    private Integer taskId;
    private Integer teacherId;
    private String status;

    private Student student;
    private Teacher teacher;
    private Task task;

    public PersonalTask(Integer id, Integer studentId, Integer taskId, Integer teacherId, String status) {
        this.id = id;
        this.studentId = studentId;
        this.taskId = taskId;
        this.teacherId = teacherId;
        this.status = status;
    }

    public PersonalTask(Integer studentId, Integer taskId, Integer teacherId, String status) {
        this.studentId = studentId;
        this.taskId = taskId;
        this.teacherId = teacherId;
        this.status = status;
    }

    public static PersonalTask from(PersonalTaskForm personalTaskForm){
        return PersonalTask.builder()
                .studentId(personalTaskForm.getStudentId())
                .taskId(personalTaskForm.getTaskId())
                .teacherId(personalTaskForm.getTeacherId())
                .status(personalTaskForm.getStatus())
                .build();
    }

    public static PersonalTask fromAllParams(PersonalTaskForm personalTaskForm){
        return PersonalTask.builder()
                .id(personalTaskForm.getId())
                .taskId(personalTaskForm.getTaskId())
                .teacherId(personalTaskForm.getTeacherId())
                .status(personalTaskForm.getStatus())
                .build();
    }
}
