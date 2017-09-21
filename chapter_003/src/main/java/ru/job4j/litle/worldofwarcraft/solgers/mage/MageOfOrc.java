package ru.job4j.litle.worldofwarcraft.solgers.mage;

import ru.job4j.litle.worldofwarcraft.solgers.Soldier;

import java.util.List;

/**
 * .
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 20.09.17;
 * @version $Id$
 * @since 0.1
 */
public class MageOfOrc extends Soldier implements Mage {
    private double baf = -1.5;
    public MageOfOrc() {
        super("Шаман");
    }
    private  boolean isWarrior = false;

    public boolean isWarrior() {
        return isWarrior;
    }
    @Override
    public List<Soldier> magiсAttack(List<Soldier> soldiersForAttack) {
        return null;
    }

    @Override
    public List<Soldier> bafSoldier(List<Soldier> soldiersForAttack) {
        return null;
    }
}
