package com.epam.modeles;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Subject {
    private Integer id;
    private String name;
    private int kurs;
}
