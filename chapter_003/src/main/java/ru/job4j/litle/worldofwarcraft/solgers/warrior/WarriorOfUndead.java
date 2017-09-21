package ru.job4j.litle.worldofwarcraft.solgers.warrior;

import ru.job4j.litle.worldofwarcraft.solgers.Soldier;

import java.util.List;

/**
 * .
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 20.09.17;
 * @version $Id$
 * @since 0.1
 */
public class WarriorOfUndead extends Soldier implements Warrior {
    private  boolean isWarrior = true;

    public boolean isWarrior() {
        return isWarrior;
    }
    private double meleeAttack = 18.0;

    public WarriorOfUndead() {
        super("Зомби");
    }


    @Override
    public List<Soldier> meleeAttack(List<Soldier> soldiersForAttack) {
        return null;
    }
}
