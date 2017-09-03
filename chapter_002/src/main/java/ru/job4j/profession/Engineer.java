package ru.job4j.profession;
/**
 * Engineer.
 *
 * @author Hincu Andrei (andreih1981@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Engineer extends Profession {
    /**
     * Специальность инжинера.
     */
    private String diplom;

    /**
     * Конструктор класса.
     * @param name - имя.
     * @param age - возрост.
     * @param diplom - специальность.
     */
    public Engineer(String name, int age, String diplom) {
        super(name, age);
        this.diplom = diplom;
    }

    /**
     *Создание проэкта.
     * @param diplom - диплом.
     * @return - проэкт.
     */
    public String proect(String diplom) {
       return this.getName() + " проэктирует " + diplom;
    }
}
