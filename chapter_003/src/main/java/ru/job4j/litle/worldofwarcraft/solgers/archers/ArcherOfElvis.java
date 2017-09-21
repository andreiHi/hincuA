package ru.job4j.litle.worldofwarcraft.solgers.archers;

import ru.job4j.litle.worldofwarcraft.Game;
import ru.job4j.litle.worldofwarcraft.solgers.Soldier;

import java.util.List;

/**
 * .
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 20.09.17;
 * @version $Id$
 * @since 0.1
 */
public class ArcherOfElvis extends Soldier implements Archer {
    private  boolean isWarrior = false;

    public boolean isWarrior() {
        return isWarrior;
    }

    private double meleeAttack = 3.0;
    private double rangeAttack = 7.0;

    public ArcherOfElvis() {
        super("Эльфийский лучник");
    }

    @Override
    public List<Soldier> rangeAttack(List<Soldier> soldiersForAttack) {

        return null;
    }

    @Override
    public List<Soldier> meleeAttack(List<Soldier> soldiersForAttack) {

        return null;
    }


}
