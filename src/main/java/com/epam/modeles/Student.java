package com.epam.modeles;

import com.epam.forms.RegistrationForm;
import com.epam.forms.StudentForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class Student {
    private Integer id;
    private String name;
    private String surname;
    private Integer groupId;
    private String login;
    private String password;
    private List<PersonalTask> personalTasks;
    private Group group;

    public Student(String name, String surname, Integer groupId, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.groupId = groupId;
        this.login = login;
        this.password = password;
    }

    public Student(Integer id, String name, String surname, String login, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
    }

    public Student(String name, String surname, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
    }

    public Student(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Student(Integer id, String name, String surname, int groupId, String login, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.groupId = groupId;
        this.login = login;
        this.password = password;
    }

    public Student() {
    }

    public static Student from(StudentForm studentForm){
        return Student.builder()
                .login(studentForm.getLogin())
                .password(studentForm.getPassword())
                .build();
    }

    public static Student fromAllParams(RegistrationForm registrationForm){
        return Student.builder()
                .name(registrationForm.getName())
                .surname(registrationForm.getSurname())
                .groupId(registrationForm.getGroupId())
                .login(registrationForm.getLogin())
                .password(registrationForm.getPassword())
                .build();
    }
}
