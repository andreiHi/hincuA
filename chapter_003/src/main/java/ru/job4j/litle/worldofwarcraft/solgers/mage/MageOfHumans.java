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
public class MageOfHumans extends Soldier implements Mage {
    private double baf = 1.5;
    private double meleeAttack = 4.0;
    public MageOfHumans() {
        super("Маг человек");
    }
    private  boolean isWarrior = false;

    public boolean isWarrior() {
        return isWarrior;
    }
    @Override
    public List<Soldier> magiсAttack() {
        return null;
    }

    @Override
    public List<Soldier> bafSoldier() {
        System.out.println("Hello From magicElvis");
        return null;
    }
}
