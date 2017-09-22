package ru.job4j.litle.worldofwarcraft.solgers.mage;

import ru.job4j.litle.worldofwarcraft.Attacks;
import ru.job4j.litle.worldofwarcraft.solgers.Soldier;
import java.util.List;

/**
 * Маг эльфов.
 * @author Hincu Andrei (andreih1981@gmail.com) by 20.09.17;
 * @version $Id$
 * @since 0.1
 */
public class MageOfElvis extends Soldier implements Mage {
    /**
     * Сила атаки.
     */
    private double magicAttack = 10.0;
    /**
     * Размер бафа.
     */
    private double baff = 0.5;
    /**
     * Тип урона.
     */
    private String typeOfBytlle = "урон магией";
    /**
     * Тип атаки.
     */
    private String bufSoldier = "улучшает характеристики";

    /**
     * Конструктор.
     */
    public MageOfElvis() {
        super("Эльфийский маг");
    }

    /**
     * Урон магией.
     * @param soldiersForAttack отряд противника.
     */
    @Override
    public void magicAttack(List<Soldier> soldiersForAttack) {
        double damge = poverOfDamage(magicAttack);
        List<Soldier> soldierList = Attacks.attack(soldiersForAttack, damge, this.getName(), typeOfBytlle);
        getGame().setOrda(soldierList);
    }

    /**
     * Баф союзника.
     * @param soldiersForAttack отряд союзников.
     */
    @Override
    public void bafSoldier(List<Soldier> soldiersForAttack) {
        double baf = poverOfDamage(baff);
        List<Soldier> soldierList = Attacks.bufSoldiers(soldiersForAttack, baf, this.getName(), bufSoldier);
        getGame().setAlians(soldierList);
    }
}
