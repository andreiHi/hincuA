package ru.job4j.litle.worldofwarcraft;

import ru.job4j.litle.worldofwarcraft.solgers.Soldier;

import java.util.ArrayList;
import java.util.List;

/**
 * .
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 22.09.17;
 * @version $Id$
 * @since 0.1
 */
public class Attacks {

    public static List<Soldier> attack(List<Soldier>soldiersForAttack, double magicAttack, String name, String typeOfBattle) {
        List<Soldier>soldierList = new ArrayList<>(soldiersForAttack);
        int index = RandomAndTeamsSettings.getRandomInt(0, soldiersForAttack.size());
        Soldier soldier = soldierList.get(index);
        soldier.damage(magicAttack);
        Game.builder.append(name).append(" ").append("наносит ").append(typeOfBattle).
                append(" ").append(magicAttack).append(" XP ").append(soldier.getGame()).
                append(Game.newLine);
        if (soldier.getHp() < 0) {
            Game.builder.append(soldier.getGame()).append("погибает").append(Game.newLine);
            soldierList.remove(soldier);
        } else {
            soldierList.set(index, soldier);
        }
        return soldierList;
    }
    public static List<Soldier> bufSoldiers(List<Soldier> soldiersForBuf, double buf, String name, String typeOfBattle ) {
        List<Soldier>soldierList = new ArrayList<>(soldiersForBuf);
        int index = RandomAndTeamsSettings.getRandomInt(0, soldiersForBuf.size());
        Soldier soldier = soldierList.get(index);
        soldier.moveToPremium();
        soldier.setGottenBaff(buf);
        soldierList.set(index, soldier);
        Game.builder.append(name).append(" ").append(typeOfBattle).append(" ").append(soldier.getName()).append(" на ").
                append(buf * 100).append("%.");
        return soldierList;
    }
}
