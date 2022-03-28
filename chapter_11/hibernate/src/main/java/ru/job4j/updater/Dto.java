package ru.job4j.updater;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dto {

    private String ssoId;
    private String lastName;
    private List<String> strings;


    public void update(Entity entity) {
        List.of(DtoInfoFields.values())
                .forEach(info -> info.update(entity, this));
    }

    public static void main(String[] args) {
        Dto dto = new Dto("123", "", Arrays.asList("123"));
        Entity entity = new Entity("first", "second", Arrays.asList("coll", "foo"));
        dto.update(entity);
        System.out.println(entity);
    }
}
