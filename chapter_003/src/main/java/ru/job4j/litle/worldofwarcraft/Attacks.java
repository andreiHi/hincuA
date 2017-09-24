package ru.job4j.litle.worldofwarcraft;

import ru.job4j.litle.worldofwarcraft.random.RandomAndTeamsSettings;
import ru.job4j.litle.worldofwarcraft.solgers.Soldier;

import java.util.ArrayList;
import java.util.List;

/**
 *Класс с методами для бафа  и атаки .
 * @author Hincu Andrei (andreih1981@gmail.com) by 22.09.17;
 * @version $Id$
 * @since 0.1
 */
public class Attacks {
    /**
     * Метод наносит урон случайному противнику.
     * @param soldiersForAttack отряд противника.
     * @param powerOfAttack сила атаки.
     * @param name имя атакующего.
     * @param typeOfBattle тип атаки.
     * @return список противника с полученным уроном.
     */
    public static List<Soldier> attack(List<Soldier> soldiersForAttack, double powerOfAttack, String name, String typeOfBattle) {
        List<Soldier> soldierList = new ArrayList<>(soldiersForAttack);
        int index = RandomAndTeamsSettings.getRandomInt(0, soldierList.size());
        Soldier soldier = soldierList.get(index);
        soldier.damage(powerOfAttack);
        Game.builder.append(name).append(" наносит ").append(typeOfBattle).
                append(" ").append(powerOfAttack).append(" XP ").append(soldier.getName()).append(".").append(Game.newLine);
        if (soldier.getHp() < 1) {
            Game.builder.append(soldier.getName()).append(" погибает.").append(Game.newLine);
            soldierList.remove(index);
        } else {
            soldierList.set(index, soldier);
        }
        return soldierList;
    }

    /**
     * Метод накладывает баф на случайного война.
     * @param soldiersForBuf отряд войнов.
     * @param buf размер бафа.
     * @param name имя мага.
     * @param typeOfBattle тип атаки.
     * @param i индекс в массиве бафающего война.
     * @return список союзников.
     */
    public static List<Soldier> bufSoldiers(List<Soldier> soldiersForBuf, double buf, String name, String typeOfBattle, int i) {
        List<Soldier> soldierList = new ArrayList<>(soldiersForBuf);
       int index;
        if (soldierList.size() > 1) {
            do {
                index = RandomAndTeamsSettings.getRandomInt(0, soldiersForBuf.size());
            } while (i == index);
        } else {
            index = RandomAndTeamsSettings.getRandomInt(0, soldiersForBuf.size());
        }
        Soldier soldier = soldierList.get(index);
        soldier.moveToPremium();
        soldier.setGottenBaff(buf);
        soldierList.set(index, soldier);
        Game.builder.append(name).append(" ").append(typeOfBattle).append(" ").append(soldier.getName()).append(" на ").
                append(buf * 100).append("%.").append(Game.newLine);
        return soldierList;
    }
}
