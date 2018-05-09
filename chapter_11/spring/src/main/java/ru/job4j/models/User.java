package ru.job4j.models;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 07.05.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class User {

    private long id;
    private String name;
    private int age;

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }
   public User(long id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public User() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("User{id= %d, name = %s, age= %d }", id, name, age);
    }
}
