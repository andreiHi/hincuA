package ru.job4j.litle.worldofwarcraft;

import ru.job4j.litle.worldofwarcraft.solgers.Soldier;
import ru.job4j.litle.worldofwarcraft.solgers.archers.Archer;
import ru.job4j.litle.worldofwarcraft.solgers.mage.Mage;
import ru.job4j.litle.worldofwarcraft.solgers.mage.MageOfUndead;
import ru.job4j.litle.worldofwarcraft.solgers.warrior.Warrior;

import java.util.List;

/**
 * Главный класс игры.
 * @author Hincu Andrei (andreih1981@gmail.com) by 20.09.17;
 * @version $Id$
 * @since 0.1
 */
public class Game {
    /**
     * Запись происходящего.
     */
    public static StringBuilder builder = new StringBuilder();
    /**
     * Новая строка.
     */
    public static String newLine = System.getProperty("line.separator");
    /**
     * Комманда орды.
     */
    private List<Soldier> orda = RandomAndTeamsSettings.getOrda();
    /**
     * Команда альянса.
     */
    private List<Soldier> alians = RandomAndTeamsSettings.getAlians();
    /**
     * Переход хода.
     */
    private boolean flag = false;

    /**
     * Флаг.
     * @return flag.
     */
    private boolean isFlag() {
        return flag;
    }

    /**
     * setter.
     * @param flag flag.
     */
    private void setFlag(boolean flag) {
        this.flag = flag;
    }

    /**
     * Установка игры всем участникам.
     */
    public void setGame() {
        for (Soldier s : orda) {
            s.setGame(this);
        }
        for (Soldier s : alians) {
            s.setGame(this);
        }
    }

    /**
     * Получить отряд орды.
     * @return отряд орды.
     */
    public List<Soldier> getOrda() {
        return orda;
    }

    /**
     * Установить комманду орды.
     * @param orda орда.
     */
    public void setOrda(List<Soldier> orda) {
        this.orda = orda;
    }

    /**
     * Геттер.
     * @return альянс.
     */
    public List<Soldier> getAlians() {
        return alians;
    }

    /**
     * Сеттер.
     * @param alians альянс.
     */
    public void setAlians(List<Soldier> alians) {
        this.alians = alians;
    }

    /**
     * Майн.
     * @param args нету.
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.setGame();
        game.start();
        System.out.println(builder);
        RandomAndTeamsSettings.writeLog(builder.toString());
    }

    /**
     * Старт.
     */
   public void start() {
        infoAboutTeams();
        whoIsFirstStartGame();
        if (isFlag()) {
            battle(alians, orda);
        } else {
            battle(orda, alians);
        }
    }

    /**
     * Битва.
     * @param teamAttack атакующая комманда.
     * @param teamForDamage получающая команда.
     */
    public void battle(List<Soldier> teamAttack, List<Soldier> teamForDamage) {

        Soldier soldierAttack = null;
        for (Soldier sol : teamAttack) {
            if (sol.isPremium()) {
                soldierAttack = sol;
            }
        }
        int index = 0;
        if (soldierAttack == null) {
            index = RandomAndTeamsSettings.getRandomInt(0, teamAttack.size());
            soldierAttack = teamAttack.get(index);
        }
        if (soldierAttack == null) {
            builder.append("ERROR");
        }
        int battle = RandomAndTeamsSettings.getRandomInt(0, 2);

        if (soldierAttack instanceof Mage) {
            if (soldierAttack instanceof MageOfUndead) {
                MageOfUndead mage = (MageOfUndead) soldierAttack;
                if (battle == 0) {
                    mage.bafSoldier(teamForDamage, index);
                } else {
                    mage.magicAttack(teamForDamage);
                }
            } else {
                Mage mage = (Mage) soldierAttack;
                if (battle == 0) {
                    mage.bafSoldier(teamAttack, index);
                } else {
                    mage.magicAttack(teamForDamage);
                }
            }
        } else if (soldierAttack instanceof Warrior) {
            Warrior warrior = (Warrior) soldierAttack;
            warrior.meleeAttack(teamForDamage);
        } else if (soldierAttack instanceof Archer) {
            Archer archer = (Archer) soldierAttack;
            if (battle == 0) {
                archer.rangeAttack(teamForDamage);
            } else {
                archer.meleeAttack(teamForDamage);
            }
        }
        List<Soldier> alians = getAlians();
        List<Soldier> orda = getOrda();
        if (!alians.isEmpty() && !orda.isEmpty()) {
            replaceFlag();
            if (isFlag()) {
                battle(alians, orda);
            } else {
                battle(orda, alians);
            }
        } else if (alians.isEmpty()) {
            builder.append("Отряд орды Победил!!").append(newLine).append("Выжевшие герои :").append(newLine);
            for (Soldier soldier : orda) {
                builder.append(soldier.getName()).append(" XP: ").append(soldier.getHp()).append(newLine);
            }
        } else {
            builder.append("Отряд альянса Победил!!").append(newLine).append("Выжевшие герои :").append(newLine);
            for (Soldier soldier : alians) {
                builder.append(soldier.getName()).append(" XP: ").append(soldier.getHp()).append(newLine);
            }
        }
    }

    /**
     * Кто первым начнет игру.
     */
    private void whoIsFirstStartGame() {
        builder.append("Атаку начал первым отряд ");
        int i = RandomAndTeamsSettings.getRandomInt(0, 2);
        if (i == 0) {
            builder.append("орды.").append(newLine).append("=============================").append(newLine);
            setFlag(false);
        } else {
            builder.append("альянса").append(newLine).append("=============================").append(newLine);
            setFlag(true);
        }
    }
    /**
     * Инфо о коммандах.
     */
    private void infoAboutTeams() {
        builder.append("Патрулируя границы Земноморья отряд альянса был аттакован отрядом орды.").append(newLine);
        builder.append("Отряды состояли из следующих войнов :").append(newLine).append("Отряд альянса :").append(newLine).append(newLine);
        for (Soldier s : alians) {
            builder.append(s.getName()).append(newLine);
        }
        builder.append(newLine).append("Отряд орды:").append(newLine);
        for (Soldier s : orda) {
            builder.append(s.getName()).append(newLine);
        }
    }

    /**
     * Чередование ходов.
     */
    private void replaceFlag() {
        if (isFlag()) {
            this.flag = false;
        } else {
            this.flag = true;
        }
    }
}


