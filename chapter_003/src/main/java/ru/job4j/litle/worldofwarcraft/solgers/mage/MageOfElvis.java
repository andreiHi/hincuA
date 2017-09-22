package ru.job4j.litle.worldofwarcraft.solgers.mage;

import ru.job4j.litle.worldofwarcraft.Attacks;
import ru.job4j.litle.worldofwarcraft.solgers.Soldier;

import java.util.ArrayList;
import java.util.List;

/**
 * .
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 20.09.17;
 * @version $Id$
 * @since 0.1
 */
public class MageOfElvis extends Soldier implements Mage {
    private double magicAttack = 10.0;
    private double baff = 0.5;
    private String typeOfBytlle = "урон магией";
    private String bufSoldier = "улучшает характеристики";
    public MageOfElvis() {
        super("Эльфийский маг");
    }

    @Override
    public void magiсAttack(List<Soldier> soldiersForAttack) {
        double damge = poverOfDamage(magicAttack);
        List<Soldier> soldierList = Attacks.attack(soldiersForAttack, damge, this.getName(), typeOfBytlle);
        getGame().setOrda(soldierList);
    }

    @Override
    public void bafSoldier(List<Soldier> soldiersForAttack) {
        double baf = poverOfDamage(baff);
        List<Soldier>soldierList = Attacks.bufSoldiers(soldiersForAttack, baf, this.getName(), bufSoldier);
        getGame().setAlians(soldierList);

    }
}
