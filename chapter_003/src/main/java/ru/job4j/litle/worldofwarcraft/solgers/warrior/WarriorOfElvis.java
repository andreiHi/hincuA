package ru.job4j.litle.worldofwarcraft.solgers.warrior;

import ru.job4j.litle.worldofwarcraft.Attacks;
import ru.job4j.litle.worldofwarcraft.solgers.Soldier;

import java.util.List;

/**
 * Эльф воин.
 * @author Hincu Andrei (andreih1981@gmail.com) by 20.09.17;
 * @version $Id$
 * @since 0.1
 */
public class WarriorOfElvis extends Soldier implements Warrior {
    /**
     * Удар мечом.
     */
    private double meleeAttack = 15.0;

    /**
     * Конструктор.
     */
    public WarriorOfElvis() {
        super("Альянс Эльфийский Воин");
    }
    /**
     * Тип урона.
     */
    private String hitOfSword = "урон мечом";
    /**
     * Атака противника.
     * @param soldiersForAttack отряд противника.
     */
    @Override
    public void meleeAttack(List<Soldier> soldiersForAttack) {
        double damge = poverOfDamage(meleeAttack);
        List<Soldier> soldierList = Attacks.attack(soldiersForAttack, damge, this.getName(), hitOfSword);
        getGame().setTeamOfOrda(soldierList);
    }


}
