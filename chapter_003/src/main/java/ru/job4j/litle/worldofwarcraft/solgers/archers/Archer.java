package ru.job4j.litle.worldofwarcraft.solgers.archers;
import ru.job4j.litle.worldofwarcraft.solgers.Soldier;

import java.util.List;

/**
 *Лучник .
 * @author Hincu Andrei (andreih1981@gmail.com) by 20.09.17;
 * @version $Id$
 * @since 0.1
 */
public interface Archer {
    /**
     * Атака из лука.
     * @param soldiersForAttack отряд противника.
     */
    void rangeAttack(List<Soldier> soldiersForAttack);

    /**
     * Атака мечом.
     * @param soldiersForAttack отряд протиника.
     */
    void meleeAttack(List<Soldier> soldiersForAttack);
}
