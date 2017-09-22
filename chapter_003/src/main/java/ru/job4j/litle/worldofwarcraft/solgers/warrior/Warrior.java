package ru.job4j.litle.worldofwarcraft.solgers.warrior;

import ru.job4j.litle.worldofwarcraft.solgers.Soldier;

import java.util.List;

/**
 * Воин.
 * @author Hincu Andrei (andreih1981@gmail.com) by 20.09.17;
 * @version $Id$
 * @since 0.1
 */
public interface Warrior {
    /**
     * Атака.
     * @param soldiersForAttack отряд противника.
     */
    void meleeAttack(List<Soldier> soldiersForAttack);
}
