package ru.job4j.litle.worldofwarcraft.solgers.archers;

import ru.job4j.litle.worldofwarcraft.Game;
import ru.job4j.litle.worldofwarcraft.solgers.Attack;
import ru.job4j.litle.worldofwarcraft.solgers.Soldier;
import java.util.List;

/**
 * Нежить лучник.
 * @author Hincu Andrei (andreih1981@gmail.com) by 20.09.17;
 * @version $Id$
 * @since 0.1
 */
public class ArcherOfUndead extends Soldier implements Attack {
    /**
     * Набор вооружения.
     */
    private final Weapon[] weapons = {new Weapon("урон от выстрела из лука", 4.0), new Weapon("урон мечом", 2.0)};

    /**
     * Конструктор.
     */
    public ArcherOfUndead() {
        super("Орда Охотник нежить");
    }

    /**
     *  Метод атаки вражеского солдата.
     * @param team  команда союзников.
     * @param teamForAttack команда противников.
     */
    @Override
    public void attack(List<Soldier> team, List<Soldier> teamForAttack) {
        Weapon weapon = selectWeapon(weapons);
        double damage = poverOfDamage(weapon.getDamage());
        Soldier soldier = selectTarget(teamForAttack);
        soldier.damage(damage);
        Game.builder.append(this.getName()).append(" наносит ").append(weapon.getName()).append(" ").
                append(damage).append(" XP ").append(soldier.getName()).append(Game.newLine);
    }
}
