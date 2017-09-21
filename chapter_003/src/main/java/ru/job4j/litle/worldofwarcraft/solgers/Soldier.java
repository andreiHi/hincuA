package ru.job4j.litle.worldofwarcraft.solgers;

import ru.job4j.litle.worldofwarcraft.Game;
import ru.job4j.litle.worldofwarcraft.RandomAndTeamsSettings;

import java.util.List;

/**
 *Воин .
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 20.09.17;
 * @version $Id$
 * @since 0.1
 */
public abstract class Soldier {
    Game game;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    double hp = 100;
    String name;
    boolean premium;

    public boolean isPremium() {
        return premium;
    }

    public Soldier(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getHp() {
        return hp;
    }
    public void moveToPremium() {
        this.premium = true;
    }
    public void moveFromPremium() {
        this.premium = false;
    }
    public void damage(double value) {
        this.hp -= value;
    }

    @Override
    public String toString() {
        return "Soldier{" +
                "name='" + name + '\'' +
                '}';
    }
}
