package ru.job4j.litle.worldofwarcraft.solgers.mage;

import ru.job4j.litle.worldofwarcraft.solgers.Soldier;

import java.util.List;

/**
 * Маг.
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 20.09.17;
 * @version $Id$
 * @since 0.1
 */
public interface Mage {
    void magiсAttack(List<Soldier> soldiersForAttack);
    void bafSoldier(List<Soldier> soldiersForAttack);
}
