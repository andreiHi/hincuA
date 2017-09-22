package ru.job4j.litle.worldofwarcraft;

import ru.job4j.litle.worldofwarcraft.solgers.Soldier;
import ru.job4j.litle.worldofwarcraft.solgers.archers.ArcherOfElvis;
import ru.job4j.litle.worldofwarcraft.solgers.archers.ArcherOfHumans;
import ru.job4j.litle.worldofwarcraft.solgers.archers.ArcherOfUndead;
import ru.job4j.litle.worldofwarcraft.solgers.archers.ArcherOrc;
import ru.job4j.litle.worldofwarcraft.solgers.mage.MageOfElvis;
import ru.job4j.litle.worldofwarcraft.solgers.mage.MageOfHumans;
import ru.job4j.litle.worldofwarcraft.solgers.mage.MageOfOrc;
import ru.job4j.litle.worldofwarcraft.solgers.mage.MageOfUndead;
import ru.job4j.litle.worldofwarcraft.solgers.warrior.WarriorOfElvis;
import ru.job4j.litle.worldofwarcraft.solgers.warrior.WarriorOfHumans;
import ru.job4j.litle.worldofwarcraft.solgers.warrior.WarriorOfOrc;
import ru.job4j.litle.worldofwarcraft.solgers.warrior.WarriorOfUndead;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *Класс формирования команд.
 * @author Hincu Andrei (andreih1981@gmail.com) by 21.09.17;
 * @version $Id$
 * @since 0.1
 */
public class RandomAndTeamsSettings {
    /**
     * Список типов войнов альянса.
     */
    private static List<Soldier> alians = new ArrayList<>();
    /**
     * Список типов войнов орды.
     */
    private static List<Soldier> orda = new ArrayList<>();

    /**
     * Инициализация списков.
     */
    static {
        alians.add(new MageOfElvis());
        alians.add(new MageOfHumans());
        alians.add(new ArcherOfElvis());
        alians.add(new ArcherOfHumans());
        alians.add(new WarriorOfElvis());
        alians.add(new WarriorOfHumans());

        orda.add(new MageOfUndead());
        orda.add(new MageOfOrc());
        orda.add(new ArcherOfUndead());
        orda.add(new ArcherOrc());
        orda.add(new WarriorOfUndead());
        orda.add(new WarriorOfOrc());
    }

    /**
     * Генератор случайных чисел в диапазоне.
     * @param from от.
     * @param to до.
     * @return случайное число.
     */
    public static int getRandomInt(int from, int to) {
        return (from + new Random().nextInt(to));
    }

    /**
     * Геттер.
     * @return Альянс.
     */
    public static List<Soldier> getAlians() {
        return alians;
    }

    /**
     * Геттер.
     * @return Орда.
     */
    public static List<Soldier> getOrda() {
        return orda;
    }

    /**
     * Формирование команды в случайн ом порядке.
     * @param rases Альянс или Орда.
     * @return готовая к бою команда.
     */
    public static List<Soldier> getTeam(List<Soldier> rases) {
        List<Soldier> team = new ArrayList<>();
        team.add(rases.get(getRandomInt(0, 2)));
        for (int i = 0; i < 3; i++) {
            team.add(rases.get(getRandomInt(2, 2)));
        }
        for (int i = 0; i < 4; i++) {
            team.add(rases.get(getRandomInt(4, 2)));
        }
        return team;
    }

}
