package com.epam.modeles;

import com.epam.forms.RegistrationForm;
import com.epam.forms.TeacherForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class Teacher {
    private Integer id;
    private String name;
    private String surname;
    private String spezialization;
    private String login;
    private String password;
    private List<Student> students;
    private List<PersonalTask> personalTasks;

    public Teacher() {
    }

    public static Teacher from(TeacherForm teacherForm) {
        return Teacher.builder()
                .login(teacherForm.getLogin())
                .password(teacherForm.getPassword())
                .build();
    }

    public Teacher(Integer id, String name, String surname, String spezialization, String login, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.spezialization = spezialization;
        this.login = login;
        this.password = password;
    }

    public static Teacher fromAllParams(RegistrationForm registrationForm){
        return Teacher.builder()
                .name(registrationForm.getName())
                .surname(registrationForm.getSurname())
                .spezialization(registrationForm.getSpecialization())
                .login(registrationForm.getLogin())
                .password(registrationForm.getPassword())
                .build();
    }
}
