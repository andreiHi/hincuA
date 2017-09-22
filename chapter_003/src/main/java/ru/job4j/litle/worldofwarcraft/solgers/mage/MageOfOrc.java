package ru.job4j.litle.worldofwarcraft.solgers.mage;

import ru.job4j.litle.worldofwarcraft.Attacks;
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
public class MageOfOrc extends Soldier implements Mage {
    private double baff = 0.5;
    public MageOfOrc() {
        super("Шаман");
    }
    private String bufSoldier = "улучшает характеристики";

    @Override
    public void magiсAttack(List<Soldier> soldiersForAttack) {
        double debaf = poverOfDamage(baff);
        List<Soldier> soldierList = new ArrayList<>(soldiersForAttack);
        Soldier soldier = null;
        int index = 0;
        for (Soldier s : soldierList) {
            if (s.isPremium()) {
                soldier = s;
                index = soldierList.indexOf(s);
                break;
            }
        }
        soldier.moveFromPremium();
        soldierList.set(index, soldier);

    }

    @Override
    public void bafSoldier(List<Soldier> soldiersForAttack) {
        double baf = poverOfDamage(baff);
        List<Soldier>soldierList = Attacks.bufSoldiers(soldiersForAttack, baf, this.getName(), bufSoldier);
        getGame().setOrda(soldierList);
    }
}
