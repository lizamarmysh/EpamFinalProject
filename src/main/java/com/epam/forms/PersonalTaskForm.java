package com.epam.forms;

import com.epam.modeles.PersonalTask;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class PersonalTaskForm {
    List<PersonalTask> personalTasks;
    private Integer id;
    private Integer studentId;
    private Integer taskId;
    private Integer teacherId;
    private String status;

    public PersonalTaskForm(Integer id, Integer studentId, Integer taskId, Integer teacherId, String status) {
        this.id = id;
        this.studentId = studentId;
        this.taskId = taskId;
        this.teacherId = teacherId;
        this.status = status;
    }

    public PersonalTaskForm(List<PersonalTask> personalTasks) {
        this.personalTasks = personalTasks;
    }

    public PersonalTaskForm(Integer studentId, Integer taskId, Integer teacherId, String status) {
        this.studentId = studentId;
        this.taskId = taskId;
        this.teacherId = teacherId;
        this.status = status;
    }

    public PersonalTaskForm() {
    }
}
