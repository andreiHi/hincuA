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
     * @return game.
     */
    public Game getGame() {
        return game;
    }

    /**
     * setter.
     * @param game установить одну игру всем обьектам.
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * хит поинт.
     */
    private double hp = 100;
    /**
     * Имя.
     */
    private  String name;

    /**
     * Геттер проклятья.
     * @return yes or no.
     */
    public boolean isCurse() {
        return curse;
    }

    /**
     * Сеттер проклятья.
     * @param curse.
     */
    public void setCurse(boolean curse) {
        this.curse = curse;
    }

    /**
     *  премиум и проклятье.
     */
    private boolean premium, curse;
    /**
     *полученный баф.
     */
    private double gottenBaff = 0;

    /**
     * геттер баф.
     * @return баф.
     */
    public double getGottenBaff() {
        return gottenBaff;
    }

    /**
     * сбросить баф.
     */
    public void resetBuff() {
        this.gottenBaff = 0;
    }

    /**
     * Установит баф.
     * @param gottenBaff баф.
     */
    public void setGottenBaff(double gottenBaff) {
        this.gottenBaff = gottenBaff;
    }

    /**
     * Премиум воин.
     * @return да нет.
     */
    public boolean isPremium() {
        return premium;
    }

    /**
     * Конструктор.
     * @param name имя.
     */
    public Soldier(String name) {
        this.name = name;
    }

    /**
     * Геттер.
     * @return имя.
     */
    public String getName() {
        return name;
    }

    /**
     * геттер Хп.
     * @return Хп.
     */
    public double getHp() {
        return hp;
    }

    /**
     * перевести в премиум.
     */
    public void moveToPremium() {
        this.premium = true;
    }

    /**
     * Убрать с премиума.
     */
    public void moveFromPremium() {
        this.premium = false;
    }

    /**
     * Полученный урон.
     * @param value урон.
     */
    public void damage(double value) {
        this.hp = this.hp - value;
    }

    /**
     * То стринг.
     * @return Стринг.
     */
    @Override
    public String toString() {
        return "Soldier{"
                + "name='"
                + name
                + '\''
                + '}';
    }

    /**
     * Уровень урона или бафа.
     * @param damage урон.
     * @return урон в зависимости от бафов или дебафов.
     */
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

    /**
     * Equals.
     * @param o o.
     * @return yes or no.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Soldier soldier = (Soldier) o;

        if (Double.compare(soldier.hp, hp) != 0) {
            return false;
        }
        if (premium != soldier.premium) {
            return false;
        }
        if (curse != soldier.curse) {
            return false;
        }
        if (Double.compare(soldier.gottenBaff, gottenBaff) != 0) {
            return false;
        }
        if (game != null ? !game.equals(soldier.game) : soldier.game != null) {
            return false;
        }
        return name != null ? name.equals(soldier.name) : soldier.name == null;
    }

    /**
     * hescjlt.
     * @return code.
     */
    @Override
    public int hashCode() {
        int result;
        long temp;
        result = game != null ? game.hashCode() : 0;
        temp = Double.doubleToLongBits(hp);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (premium ? 1 : 0);
        result = 31 * result + (curse ? 1 : 0);
        temp = Double.doubleToLongBits(gottenBaff);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
