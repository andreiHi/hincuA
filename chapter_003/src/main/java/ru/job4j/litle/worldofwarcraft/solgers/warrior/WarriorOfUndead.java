package ru.job4j.litle.worldofwarcraft.solgers.warrior;

import ru.job4j.litle.worldofwarcraft.Attacks;
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
    /**
     * Урон.
     */
    private double meleeAttack = 18.0;

    /**
     * Конструктор.
     */
    public WarriorOfUndead() {
        super("Зомби");
    }
    /**
     * Тип урона.
     */
    private String hitOfSword = "урон копьём";
    /**
     * Атака.
     * @param soldiersForAttack отряд противника.
     */
    @Override
    public void meleeAttack(List<Soldier> soldiersForAttack) {
        double damge = poverOfDamage(meleeAttack);
        List<Soldier> soldierList = Attacks.attack(soldiersForAttack, damge, this.getName(), hitOfSword);
        getGame().setAlians(soldierList);
    }
}
