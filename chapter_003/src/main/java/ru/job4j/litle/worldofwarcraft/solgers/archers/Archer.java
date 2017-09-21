package ru.job4j.litle.worldofwarcraft.solgers.archers;

import ru.job4j.litle.worldofwarcraft.solgers.Soldier;

import java.util.List;

/**
 *лучник .
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 20.09.17;
 * @version $Id$
 * @since 0.1
 */
public interface Archer {
    List<Soldier> rangeAttack(List<Soldier> soldiersForAttack);
    List<Soldier> meleeAttack(List<Soldier> soldiersForAttack);
}
