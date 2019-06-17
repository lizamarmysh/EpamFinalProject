package com.epam.modeles;

import com.epam.forms.TaskForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Task {
    private Integer id;
    private String name;
    private int subjectId;
    private String text;

    private Subject subject;

    public Task(int id, String name, int subjectId, String text){
        this.id = id;
        this.name = name;
        this.subjectId = subjectId;
        this.text = text;
    }

    public Task(String name, int subjectId, String text) {
        this.name = name;
        this.subjectId = subjectId;
        this.text = text;
    }

    public static Task from(TaskForm taskForm){
        return Task.builder()
                .name(taskForm.getTaskName())
                .subjectId(taskForm.getSubjectId())
                .text(taskForm.getTaskText())
                .build();
    }
}
