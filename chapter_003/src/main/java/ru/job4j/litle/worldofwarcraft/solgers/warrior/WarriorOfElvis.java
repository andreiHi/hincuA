package ru.job4j.litle.worldofwarcraft.solgers.warrior;

import ru.job4j.litle.worldofwarcraft.solgers.Soldier;
import java.util.List;

/**
 * Эльф воин.
 * @author Hincu Andrei (andreih1981@gmail.com) by 20.09.17;
 * @version $Id$
 * @since 0.1
 */
public class WarriorOfElvis extends Soldier {
    /**
     * Набор вооружения.
     */
    private final Weapon[] weapons = {new Weapon("урон мечом", 15.0)};
    /**
     * Конструктор.
     */
    public WarriorOfElvis() {
        super("Альянс Эльфийский Воин");
    }

    /**
     *  Метод атаки вражеского солдата.
     * @param team  команда союзников.
     * @param teamForAttack команда противников.
     * @return строка для логера.
     */
    @Override
    public String attack(List<Soldier> team, List<Soldier> teamForAttack) {
        double damage = poverOfDamage(selectWeapon(weapons).getDamage());
        Soldier soldier = selectTarget(teamForAttack);
        soldier.damage(damage);
        return String.format("%s наносит %s %d XP противнику %s",this.getName(), weapons[0].getName(),
                damage, soldier.getName());
    }
}
