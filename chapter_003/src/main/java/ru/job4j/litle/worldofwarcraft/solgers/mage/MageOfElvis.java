package ru.job4j.litle.worldofwarcraft.solgers.mage;

import ru.job4j.litle.worldofwarcraft.Game;
import ru.job4j.litle.worldofwarcraft.solgers.Attack;
import ru.job4j.litle.worldofwarcraft.solgers.Soldier;
import java.util.List;

/**
 * Маг эльфов.
 * @author Hincu Andrei (andreih1981@gmail.com) by 20.09.17;
 * @version $Id$
 * @since 0.1
 */
public class MageOfElvis extends Soldier implements Attack {
    /**
     * Набор вооружения.
     */
    private final Weapon[] weapons = {new Weapon("урон магией", 10.0), new Weapon("улучшение характеристик", 0.5)};

    /**
     * Конструктор.
     */
    public MageOfElvis() {
        super("Альянс Эльфийский маг");
    }

    /**
     * Метод атаки вражеского солдата.
     * @param team союзная команда.
     * @param teamForAttack вражеская команда.
     */
    @Override
    public void attack(List<Soldier> team, List<Soldier> teamForAttack) {
        Weapon weapon = selectWeapon(weapons);
        if (weapon.equals(weapons[0])) {
            double damage = poverOfDamage(weapon.getDamage());
            Soldier soldier = selectTarget(teamForAttack);
            soldier.damage(damage);
            Game.builder.append(this.getName()).append(" наносит ").append(weapons[0].getName()).append(" ").
                    append(damage).append(" XP ").append(soldier.getName()).append(Game.newLine);
        } else {
            Soldier soldier = selectTarget(team);
            soldier.moveToPremium();
            Game.builder.append(this.getName()).append(" ").append(weapon.getName()).append(" ").append(soldier.getName()).append(" на ").
                    append(soldier.getPremium()).append("%.").append(Game.newLine);
        }
    }
}
