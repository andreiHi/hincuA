package ru.job4j.litle.worldofwarcraft.solgers.archers;

import ru.job4j.litle.worldofwarcraft.solgers.Soldier;

import java.util.List;

/**
 * .
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 20.09.17;
 * @version $Id$
 * @since 0.1
 */
public class ArcherOfUndead extends Soldier implements Archer {
    private double meleeAttack = 2.0;
    private double rangeAttack = 4.0;
    private  boolean isWarrior = false;

    public boolean isWarrior() {
        return isWarrior;
    }
    public ArcherOfUndead() {
        super("Нежить лучник");
    }

    @Override
    public List<Soldier> rangeAttack() {
        return null;
    }

    @Override
    public List<Soldier> meleeAttack() {
        return null;
    }
}