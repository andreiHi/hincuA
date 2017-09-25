package ru.job4j.litle.worldofwarcraft.solgers.archers;
import ru.job4j.litle.worldofwarcraft.Attacks;
import ru.job4j.litle.worldofwarcraft.solgers.Attack;
import ru.job4j.litle.worldofwarcraft.solgers.Soldier;

import java.util.List;

/**
 *Лучник эльфов.
 * @author Hincu Andrei (andreih1981@gmail.com) by 20.09.17;
 * @version $Id$
 * @since 0.1
 */
public class ArcherOfElvis extends Soldier implements Attack {
    /**
     * Конструктор.
     */
    public ArcherOfElvis() {
        super("Альянс Эльфийский лучник");
    }
    /**
     * Атака мечом.
     */
    private double meleeAttack = 3.0;
    /**
     * Атака из лука.
     */
    private double rangeAttack = 7.0;
    /**
     * Тип урона.
     */
    private String hitOfSword = "урон мечом";
    /**
     * Тип атаки.
     */
    private String hitOfArcher = "урон от выстрела из лука";

    @Override
    public List<Soldier> ferstAttack(List<Soldier> soldiersForAttack) {

        double damge = poverOfDamage(rangeAttack);
        List<Soldier> soldierList = Attacks.attack(soldiersForAttack, damge, this.getName(), hitOfArcher);
        return soldierList;
    }

    @Override
    public List<Soldier> SecondAttack(List<Soldier> soldiersForAttack) {
        double damge = poverOfDamage(meleeAttack);
        List<Soldier> soldierList = Attacks.attack(soldiersForAttack, damge, this.getName(), hitOfSword);
        return soldierList;
    }
}
