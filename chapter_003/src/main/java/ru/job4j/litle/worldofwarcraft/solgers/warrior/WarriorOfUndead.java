package ru.job4j.litle.worldofwarcraft.solgers.warrior;

import ru.job4j.litle.worldofwarcraft.Game;
import ru.job4j.litle.worldofwarcraft.solgers.Attack;
import ru.job4j.litle.worldofwarcraft.solgers.Soldier;
import java.util.List;

/**
 *Воин нежить.
 * @author Hincu Andrei (andreih1981@gmail.com) by 20.09.17;
 * @version $Id$
 * @since 0.1
 */
public class WarriorOfUndead extends Soldier implements Attack {
    /**
     * Набор вооружения.
     */
    private  final Weapon[] weapons = {new Weapon("урон копьем", 18)};
    /**
     * Конструктор.
     */
    public WarriorOfUndead() {
        super("Орда Зомби");
    }
    /**
     * Метод атаки вражеского солдата.
     * @param team союзная команда.
     * @param teamForAttack вражеская команда.
     */
    @Override
    public void attack(List<Soldier> team, List<Soldier> teamForAttack) {
        double damage = poverOfDamage(selectWeapon(weapons).getDamage());
        Soldier soldier = selectTarget(teamForAttack);
        soldier.damage(damage);
        Game.builder.append(this.getName()).append(" наносит ").append(weapons[0].getName()).append(" ").
                append(damage).append(" XP ").append(soldier.getName()).append(Game.newLine);
    }
}
