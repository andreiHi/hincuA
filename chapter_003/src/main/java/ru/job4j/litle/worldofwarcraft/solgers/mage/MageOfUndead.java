package ru.job4j.litle.worldofwarcraft.solgers.mage;

import ru.job4j.litle.worldofwarcraft.Attacks;
import ru.job4j.litle.worldofwarcraft.Game;
import ru.job4j.litle.worldofwarcraft.random.RandomAndTeamsSettings;
import ru.job4j.litle.worldofwarcraft.solgers.Soldier;

import java.util.ArrayList;
import java.util.List;

/**
 *Класс Некроманта .
 * @author Hincu Andrei (andreih1981@gmail.com) by 20.09.17;
 * @version $Id$
 * @since 0.1
 */
public class MageOfUndead extends Soldier implements Mage {
    /**
     * Размер магического урона.
     */
    private double magicAttack = 5.0;
    /**
     * Название типа атаки.
     */
    private String typeOfBytlle = "урон магией";

    /**
     * Конструктор.
     */
    public MageOfUndead() {
        super("Орда Некромант");
    }

    /**
     * Урон магией случайного противника.
     * @param soldiersForAttack
     */
    @Override
    public void magicAttack(List<Soldier> soldiersForAttack) {
        double damge = poverOfDamage(magicAttack);
        List<Soldier> soldierList = Attacks.attack(soldiersForAttack, damge, this.getName(), typeOfBytlle);
        getGame().setTeamOfAlians(soldierList);
    }

    /**
     * Наложение проклятья на случайного противника.
     * @param soldiersForAttack список противников.
     */
    @Override
    public void bafSoldier(List<Soldier> soldiersForAttack, int i) {
        List<Soldier> soldierList = new ArrayList<>(soldiersForAttack);
        int index = RandomAndTeamsSettings.getRandomInt(0, soldiersForAttack.size());
        Soldier soldier = soldierList.get(index);
        soldier.setCurse(true);
        soldierList.set(index, soldier);
        Game.builder.append(getName()).append(" накладывает проклятье на ").append(soldier.getName()).append(Game.newLine);
        getGame().setTeamOfAlians(soldierList);

    }
}
