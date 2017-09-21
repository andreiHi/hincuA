package ru.job4j.litle.worldofwarcraft;

import ru.job4j.litle.worldofwarcraft.solgers.Soldier;
import ru.job4j.litle.worldofwarcraft.solgers.archers.Archer;
import ru.job4j.litle.worldofwarcraft.solgers.mage.Mage;
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
    private StringBuilder builder = new StringBuilder();
    private String newLine = System.getProperty("line.separator");
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
            while (true) {
                battle(orda, alians);
                battle(alians, orda);
                break;
            }
        } else {
            while (true) {
                battle(alians, orda);
                battle(orda, alians);
                break;
            }
        }

    }

    public void battle(List<Soldier> soldiersAttack, List<Soldier> forAttacs) {
        Soldier soldierAttack = null;
        for (Soldier sol : soldiersAttack) {
            if (sol.isPremium()) {
                soldierAttack = sol;
            }
        }
        if (soldierAttack == null) {
            int index = RandomAndTeamsSettings.getRandomInt(0, soldiersAttack.size());
            soldierAttack = soldiersAttack.get(index);
        }
        int battle = RandomAndTeamsSettings.getRandomInt(0, 2);
        if (soldierAttack instanceof Mage) {
            Mage mage = (Mage) soldierAttack;
            if (battle == 0) {
                mage.bafSoldier();
                System.out.println("log");
            }else mage.magiсAttack();
            System.out.println("log1");
        }else if (soldierAttack instanceof Warrior) {
            Warrior warrior = (Warrior) soldierAttack;
            warrior.meleeAttack();
            System.out.println("log2");
        } else if (soldierAttack instanceof Archer) {
            Archer archer = (Archer) soldierAttack;
            if (battle == 0) {
                archer.rangeAttack();
                System.out.println("log3");
            } else {
                System.out.println("log4");
                archer.meleeAttack();
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

