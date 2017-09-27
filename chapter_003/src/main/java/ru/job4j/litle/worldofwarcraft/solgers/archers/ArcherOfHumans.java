package ru.job4j.litle.worldofwarcraft.solgers.archers;

import ru.job4j.litle.worldofwarcraft.solgers.Attack;
import ru.job4j.litle.worldofwarcraft.solgers.Soldier;

import java.util.List;

/**
 *Лучник человек.
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 20.09.17;
 * @version $Id$
 * @since 0.1
 */
public class ArcherOfHumans extends Soldier implements Attack {
    /**
     * Набор вооружения.
     */
    private final Weapon[] weapons = {new Weapon("урон от выстрела из арбалета", 5.0),
            new Weapon("урон мечом", 3.0)
    };

    /**
     *Конструктор.
     */
    public ArcherOfHumans() {
        super("Альянс Арбалетчик");
    }
    /**
     *  Метод атаки вражеского солдата.
     * @param team  команда союзников.
     * @param teamForAttack команда противников.
     * @return строка для логера.
     */
    @Override
    public String attack(List<Soldier> team, List<Soldier> teamForAttack) {
        Weapon weapon = selectWeapon(weapons);
        double damage = poverOfDamage(weapon.getDamage());
        Soldier soldier = selectTarget(teamForAttack);
        soldier.damage(damage);
        return String.format("%s наносит %s %d XP противнику %s",this.getName(), weapon.getName(),
                damage, soldier.getName());
    }

}
