package ru.job4j.litle.worldofwarcraft.solgers.mage;

import ru.job4j.litle.worldofwarcraft.Game;
import ru.job4j.litle.worldofwarcraft.RandomAndTeamsSettings;
import ru.job4j.litle.worldofwarcraft.solgers.Soldier;

import java.util.ArrayList;
import java.util.List;

/**
 * .
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 20.09.17;
 * @version $Id$
 * @since 0.1
 */
public class MageOfElvis extends Soldier implements Mage {
    private double baf = 1.5;
    private double magicAttack = 10.0;
    public MageOfElvis() {
        super("Эльфийский маг");
    }

    @Override
    public List<Soldier> magiсAttack(List<Soldier> soldiersForAttack) {
        List<Soldier>soldierList = new ArrayList<>(soldiersForAttack);
        if (isPremium()) {
            magicAttack = magicAttack * 1.5;
            moveFromPremium();
        }
        int index = RandomAndTeamsSettings.getRandomInt(0, soldiersForAttack.size());
        String name = this.getName();
        Soldier soldier = soldierList.get(index);
        soldier.damage(magicAttack);
        Game.builder.append(name).append(" наносит урон магией ").append(magicAttack).append(" XP ").append(soldier.getGame()).append(Game.newLine);
        if (soldier.getHp() < 0) {
            Game.builder.append(soldier.getGame()).append("погибает").append(Game.newLine);
            soldierList.remove(soldier);
        } else {
            soldierList.set(index, soldier);
        }
        getGame().setOrda(soldierList);
        return soldierList;
    }

    @Override
    public List<Soldier> bafSoldier(List<Soldier> soldiersForAttack) {

        return null;
    }
}
