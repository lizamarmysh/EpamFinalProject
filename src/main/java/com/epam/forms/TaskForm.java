package com.epam.forms;

import com.epam.modeles.Subject;
import com.epam.modeles.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class TaskForm {
    List<Task> tasks;
    private String taskName;
    private Integer subjectId;
    private String taskText;

    private Subject subject;

    public TaskForm(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskForm(String taskName, Integer subjectId, String taskText) {
        this.taskName = taskName;
        this.subjectId = subjectId;
        this.taskText = taskText;
    }

    public TaskForm() {
    }
}
