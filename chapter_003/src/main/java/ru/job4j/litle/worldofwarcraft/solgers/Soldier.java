package ru.job4j.litle.worldofwarcraft.solgers;

import ru.job4j.litle.worldofwarcraft.Game;
/**
 *Воин.
 * @author Hincu Andrei (andreih1981@gmail.com) by 20.09.17;
 * @version $Id$
 * @since 0.1
 */
public abstract class Soldier {
    /**
     * Игра.
     */
    private Game game;

    /**
     * Получить игру.
     * @return
     */
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    private double hp = 100;
    private  String name;

    public boolean isCurse() {
        return curse;
    }

    public void setCurse(boolean curse) {
        this.curse = curse;
    }

    private boolean premium, curse;
    private double gottenBaff = 0;

    public double getGottenBaff() {
        return gottenBaff;
    }
    public void resetBuff() {
        this.gottenBaff = 0;
    }
    public void setGottenBaff(double gottenBaff) {
        this.gottenBaff = gottenBaff;
    }

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
        this.hp = this.hp - value;
    }

    @Override
    public String toString() {
        return "Soldier{"
                + "name='"
                + name
                + '\''
                + '}';
    }
    public double poverOfDamage(double damage) {
        if (isPremium() && !isCurse()) {
            damage = damage + damage * getGottenBaff();
            moveFromPremium();
            resetBuff();
        } else if (isPremium() && isCurse()) {
            damage = (damage + damage * getGottenBaff()) / 2;
            moveFromPremium();
            resetBuff();
            setCurse(false);
        } else if (!isPremium() && isCurse()) {
            damage = damage / 2;
            setCurse(false);
        }
        return damage;
    }
}
