package ru.job4j.litle.worldofwarcraft.solgers.mage;

import ru.job4j.litle.worldofwarcraft.Attacks;
import ru.job4j.litle.worldofwarcraft.Game;
import ru.job4j.litle.worldofwarcraft.solgers.Soldier;

import java.util.ArrayList;
import java.util.List;

/**
 *Класс шамана.
 * @author Hincu Andrei (andreih1981@gmail.com) by 20.09.17;
 * @version $Id$
 * @since 0.1
 */
public class MageOfOrc extends Soldier implements Mage {
    /**
     * Дефолтный баф дебаф.
     */
    private double baff = 0.5;

    /**
     * Конструктор.
     */
    public MageOfOrc() {
        super("Шаман");
    }

    /**
     * Название атаки.
     */
    private String bufSoldier = "улучшает характеристики";

    /**
     * Снятие бафа с противника с улучшением.
     * @param soldiersForAttack отряд противника.
     */
    @Override
    public void magicAttack(List<Soldier> soldiersForAttack) {
        List<Soldier> soldierList = new ArrayList<>(soldiersForAttack);
        Game.builder.append("Шаман использует руну всевластия для поиска сильнейшего противника.").append(Game.newLine);
        Soldier soldier = null;
        int index = 0;
        for (Soldier s : soldierList) {
            if (s.isPremium()) {
                soldier = s;
                index = soldierList.indexOf(s);
                break;
            }
        }
        if (soldier != null) {
            soldier.moveFromPremium();
            soldierList.set(index, soldier);
            Game.builder.append("Противник найден это ").append(soldier.getName()).append(" и он лешился улучшения.").append(Game.newLine);
        } else {
            Game.builder.append("Противник не был найден Шаман потратил руну в пустую!").append(Game.newLine);
        }
    }

    /**
     * Наложени бафа на случайного война своего отряда.
     * @param soldiersForAttack отряд союзников.
     */
    @Override
    public void bafSoldier(List<Soldier> soldiersForAttack) {
        double baf = poverOfDamage(baff);
        List<Soldier> soldierList = Attacks.bufSoldiers(soldiersForAttack, baf, this.getName(), bufSoldier);
        getGame().setOrda(soldierList);
    }
}
