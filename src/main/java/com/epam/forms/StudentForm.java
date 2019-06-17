package com.epam.forms;

import com.epam.modeles.Group;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentForm {
    private String name;
    private String surname;
    private Integer groupId;
    private String login;
    private String password;

}
