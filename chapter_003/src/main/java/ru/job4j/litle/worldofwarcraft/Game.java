package ru.job4j.litle.worldofwarcraft;

import ru.job4j.litle.worldofwarcraft.solgers.Soldier;
import ru.job4j.litle.worldofwarcraft.solgers.archers.Archer;
import ru.job4j.litle.worldofwarcraft.solgers.mage.*;
import ru.job4j.litle.worldofwarcraft.solgers.warrior.Warrior;

import java.util.List;

/**
 * .
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 20.09.17;
 * @version $Id$
 * @since 0.1
 */
public class Game {
    public static StringBuilder builder = new StringBuilder();
    public static String newLine = System.getProperty("line.separator");
    private List<Soldier> orda = RandomAndTeamsSettings.getTeam(RandomAndTeamsSettings.getOrda());
    private List<Soldier> alians = RandomAndTeamsSettings.getTeam(RandomAndTeamsSettings.getAlians());
    private void setGame() {
        for (Soldier s : orda) {
            s.setGame(this);
        }
        for (Soldier s : alians) {
            s.setGame(this);
        }
    }
    public List<Soldier> getOrda() {
        return orda;
    }

    public void setOrda(List<Soldier> orda) {
        this.orda = orda;
    }

    public List<Soldier> getAlians() {
        return alians;
    }

    public void setAlians(List<Soldier> alians) {
        this.alians = alians;
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.setGame();
        game.start();
    }
    private void start() {
        infoAboutTeams();
        if (whoIsFirstStartGame()) {
            battle(orda, alians);
        }else {
            battle(alians, orda);
        }
    }

    public void battle(List<Soldier> soldiersAttack, List<Soldier> forAttacs) {
        List<Soldier>alians;
        List<Soldier>orda;
        Soldier soldierAttack = null;
        for (Soldier sol : soldiersAttack) {
            if (sol.isPremium()) {
                soldierAttack = sol;
            }
        }
        if (soldierAttack == null) {
            int index = RandomAndTeamsSettings.getRandomInt(0, soldiersAttack.size());
            System.out.println(index);
            soldierAttack = soldiersAttack.get(index);
        }
        int battle = RandomAndTeamsSettings.getRandomInt(0, 2);

        if (soldierAttack instanceof Mage) {
            if (soldierAttack instanceof MageOfUndead) {
                MageOfUndead mage = (MageOfUndead) soldierAttack;
                if (battle == 0) {
                    mage.bafSoldier(forAttacs);
                }else {
                    mage.magiсAttack(forAttacs);
                }
            }else {
                Mage mage = (Mage) soldierAttack;
                if (battle == 0) {
                    mage.bafSoldier(soldiersAttack);
                } else {
                    mage.magiсAttack(forAttacs);
                }
            }
        }else if (soldierAttack instanceof Warrior) {
            Warrior warrior = (Warrior) soldierAttack;
            warrior.meleeAttack(forAttacs);
        }else if (soldierAttack instanceof Archer) {
            Archer archer = (Archer) soldierAttack;
            if (battle == 0) {
                archer.rangeAttack(forAttacs);
            }else {
                archer.meleeAttack(forAttacs);
            }
        }
    }
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
    private boolean whoIsFirstStartGame() {
        builder.append("Атаку начал первым отряд ");
        int i = RandomAndTeamsSettings.getRandomInt(0, 2);
        if (i == 0) {
            builder.append("орды.");
        } else {
            builder.append("альянса");
        }
        System.out.println(builder);
        return i == 0;
    }
}


