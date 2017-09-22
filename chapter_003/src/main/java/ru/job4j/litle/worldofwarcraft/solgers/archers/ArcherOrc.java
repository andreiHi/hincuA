package ru.job4j.litle.worldofwarcraft.solgers.archers;

import ru.job4j.litle.worldofwarcraft.Attacks;
import ru.job4j.litle.worldofwarcraft.solgers.Soldier;

import java.util.List;

/**
 *Орк лучник.
 * @author Hincu Andrei (andreih1981@gmail.com) by 20.09.17;
 * @version $Id$
 * @since 0.1
 */
public class ArcherOrc extends Soldier implements Archer {
    /**
     * Урон клинком.
     */
    private double meleeAttack = 2.0;
    /**
     * урон от стрел.
     */
    private double rangeAttack = 3.0;

    /**
     * Конструктор.
     */
    public ArcherOrc() {
        super("Орк лучник");
    }

    /**
     * Тип урона.
     */
    private String hitOfSword = "урон клинком";
    /**
     * Тип атаки.
     */
    private String hitOfArcher = "урон от выстрела из лука";

    /**
     * Атака из лука.
     * @param soldiersForAttack отряд противника.
     */
    @Override
    public void rangeAttack(List<Soldier> soldiersForAttack) {
        double damge = poverOfDamage(rangeAttack);
        List<Soldier> soldierList = Attacks.attack(soldiersForAttack, damge, this.getName(), hitOfArcher);
        getGame().setAlians(soldierList);
    }

    /**
     * Атака клинком.
     * @param soldiersForAttack отряд протиника.
     */
    @Override
    public void meleeAttack(List<Soldier> soldiersForAttack) {
        double damge = poverOfDamage(meleeAttack);
        List<Soldier> soldierList = Attacks.attack(soldiersForAttack, damge, this.getName(), hitOfSword);
        getGame().setAlians(soldierList);
    }
}
