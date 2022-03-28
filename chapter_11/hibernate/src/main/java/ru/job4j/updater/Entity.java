package ru.job4j.updater;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entity {
    private String ssoId;
    private String lastName;
    private List<String> strings;
}
