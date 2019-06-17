package com.epam.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RegistrationForm {
    private String name;
    private String surname;
    private Integer groupId;
    private String specialization;
    private String login;

    public RegistrationForm(String name, String surname, String specialization, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.specialization = specialization;
        this.login = login;
        this.password = password;
    }

    private String password;

    public RegistrationForm(String name, String surname, Integer groupId, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.groupId = groupId;
        this.login = login;
        this.password = password;
    }
}
