package ru.job4j.litle.worldofwarcraft.solgers.archers;

import ru.job4j.litle.worldofwarcraft.Attacks;
import ru.job4j.litle.worldofwarcraft.solgers.Soldier;

import java.util.List;

/**
 *Лучник человек.
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 20.09.17;
 * @version $Id$
 * @since 0.1
 */
public class ArcherOfHumans extends Soldier implements Archer {
    /**
     * Сила атаки мечом.
     */
    private double meleeAttack = 3.0;
    /**
     * Сила атаки луком.
     */
    private double rangeAttack = 5.0;

    /**
     *Конструктор.
     */
    public ArcherOfHumans() {
        super("Альянс Арбалетчик");
    }
    /**
     * Тип урона.
     */
    private String hitOfSword = "урон мечом";
    /**
     * Тип атаки.
     */
    private String hitOfArcher = "урон от выстрела из арбалета";

    /**
     * Метод нанесения урона от стрел.
     * @param soldiersForAttack отряд противника.
     */
    @Override
    public void rangeAttack(List<Soldier> soldiersForAttack) {
        double damge = poverOfDamage(rangeAttack);
        List<Soldier> soldierList = Attacks.attack(soldiersForAttack, damge, this.getName(), hitOfArcher);
        getGame().setOrda(soldierList);
    }

    /**
     * Метод нанесения урона от меча.
     * @param soldiersForAttack отряд протиника.
     */
    @Override
    public void meleeAttack(List<Soldier> soldiersForAttack) {
        double damge = poverOfDamage(meleeAttack);
        List<Soldier> soldierList = Attacks.attack(soldiersForAttack, damge, this.getName(), hitOfSword);
        getGame().setOrda(soldierList);
    }
}
