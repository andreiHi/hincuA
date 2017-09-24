package ru.job4j.litle.worldofwarcraft.solgers.mage;

import ru.job4j.litle.worldofwarcraft.Attacks;
import ru.job4j.litle.worldofwarcraft.solgers.Soldier;
import java.util.List;

/**
 *Маг человек.
 * @author Hincu Andrei (andreih1981@gmail.com) by 20.09.17;
 * @version $Id$
 * @since 0.1
 */
public class MageOfHumans extends Soldier implements Mage {
    /**
     * Размер бафа.
     */
    private double baff = 0.5;
    /**
     * Сила атаки.
     */
    private double magicAttack = 4.0;
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
    public MageOfHumans() {
        super("Альянс Маг человек");
    }

    /**
     * Урон магией.
     * @param soldiersForAttack отряд противников.
     */
    @Override
    public void magicAttack(List<Soldier> soldiersForAttack) {
        double damge = poverOfDamage(magicAttack);
        List<Soldier> soldierList = Attacks.attack(soldiersForAttack, damge, this.getName(), typeOfBytlle);
        getGame().setTeamOfOrda(soldierList);
    }

    /**
     * Баф союзника.
     * @param soldiersForAttack отряд союзников.
     */
    @Override
    public void bafSoldier(List<Soldier> soldiersForAttack, int index) {
        double baf = poverOfDamage(baff);
        List<Soldier> soldierList = Attacks.bufSoldiers(soldiersForAttack, baf, this.getName(), bufSoldier, index);
        getGame().setTeamOfAlians(soldierList);
    }
}
