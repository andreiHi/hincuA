package ru.job4j.litle.worldofwarcraft.solgers.warrior;

import ru.job4j.litle.worldofwarcraft.Attacks;
import ru.job4j.litle.worldofwarcraft.solgers.Soldier;

import java.util.ArrayList;
import java.util.List;

/**
 * Эльф воин.
 * @author Hincu Andrei (andreih1981@gmail.com) by 20.09.17;
 * @version $Id$
 * @since 0.1
 */
public class WarriorOfElvis extends Soldier {

    private List<Weapon> weapons;
    /**
     * Конструктор.
     */
    public WarriorOfElvis() {
        super("Альянс Эльфийский Воин");
        this.weapons = new ArrayList<>();
        weapons.add(new Weapon("урон мечом", 15.0));
    }

    /**
     * Атака противника.
     * @param soldiersForAttack отряд противника.
     */

    public void meleeAttack(List<Soldier> soldiersForAttack) {
        double damge = poverOfDamage(meleeAttack);
        List<Soldier> soldierList = Attacks.attack(soldiersForAttack, damge, this.getName(), hitOfSword);
 //       getGame().setTeamOfOrda(soldierList);
    }

    @Override
    public List<Soldier> attack(List<Soldier> team, List<Soldier> teamForAttack) {
        double damage = poverOfDamage(selectWeapon(weapons).getDamage());
        Soldier soldier = selectTarget(teamForAttack);
        soldier.damage(damage);
        return null;
    }
}
