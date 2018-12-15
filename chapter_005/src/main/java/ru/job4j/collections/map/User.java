package ru.job4j.collections.map;

/**
 * Модель User.
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 15.10.17;
 * @version $Id$
 * @since 0.1
 */
public class User {
    private String name;
    private int age;
    private int children;

    public User(String name, int age, int children) {
        this.name = name;
        this.age = age;
        this.children = children;
    }

    @Override
    public String toString() {
        return "User{"
                + "name='"
                + name
                + '\''
                + ", age="
                + age
                + ", children="
                + children
                + '}';
    }

    public static void main(String[] args) {
        //в данной задаче hashCode equals не переопределены значит
        User user1 = new User("Anna", 28, 1);
        User user2 = new User("Anna", 28, 1);
        int i = user1.hashCode();
        int i2 = user2.hashCode();
        System.out.println(i); //356573597 первый усер
        System.out.println(i2); //1735600054 второй усер
        //далее на основе данного значения вычисляется хэш
        System.out.println("=======================================");
        int hash = hash(user1);
        int hash2 = hash(user2);
        System.out.println(hash); //356578525
        System.out.println(hash2); //1735606469
        System.out.println("=======================================");
        //далее на основе хэша вычисляется ячейка в массиве будем считать дефолтный размер мапы = 16
        int index = index(hash);
        int index2 = index(hash2);
        System.out.println(index); //13
        System.out.println(index2); //5
        //как видете первая пара попала в ячейку 5 а вторая в ячейку 3
        // до сравнения по методу equals дело даже не дошло
    }
    static final int hash(Object key) {
        int h;
        int i1 = (key.hashCode()) ^ (key.hashCode() >>> 16);
        int i = i1;
        return i;
    }
    static int index(int hash) {
        return (16 - 1) & hash;
    }
}
