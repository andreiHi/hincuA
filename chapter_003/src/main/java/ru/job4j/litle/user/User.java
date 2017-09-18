package ru.job4j.litle.user;
/**
 * User.
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 18.09.17;
 * @version $Id$
 * @since 0.1
 */
public class User implements Comparable<User>{
    /**
     * имя.
     */
    private String name;
    /**
     * возраст.
     */
    private int age;

    /**
     * конструктор.
     * @param name имя.
     * @param age возраст.
     */
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * как сравнивать обьекты.
     * @param user user.
     * @return -1, 0, 1.
     */
    @Override
    public int compareTo(User user) {
        return this.age - user.age;
    }

    /**
     * toString.
     * @return name + age.
     */
    @Override
    public String toString() {
        return "User{"
                + "name='"
                + name
                + '\''
                + ", age="
                + age
                + '}';
    }
}
