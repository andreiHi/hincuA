package ru.job4j.litle.worlofwarcraft;

import org.junit.Test;
import ru.job4j.litle.worldofwarcraft.Game;
import ru.job4j.litle.worldofwarcraft.solgers.Soldier;
import ru.job4j.litle.worldofwarcraft.solgers.archers.ArcherOfElvis;
import ru.job4j.litle.worldofwarcraft.solgers.archers.ArcherOfHumans;
import ru.job4j.litle.worldofwarcraft.solgers.archers.ArcherOfUndead;
import ru.job4j.litle.worldofwarcraft.solgers.archers.ArcherOrc;
import ru.job4j.litle.worldofwarcraft.solgers.mage.MageOfHumans;
import ru.job4j.litle.worldofwarcraft.solgers.mage.MageOfUndead;
import ru.job4j.litle.worldofwarcraft.solgers.warrior.WarriorOfElvis;
import ru.job4j.litle.worldofwarcraft.solgers.warrior.WarriorOfHumans;
import ru.job4j.litle.worldofwarcraft.solgers.warrior.WarriorOfOrc;
import ru.job4j.litle.worldofwarcraft.solgers.warrior.WarriorOfUndead;

import java.util.ArrayList;
import java.util.List;

/**
 * .
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 23.09.17;
 * @version $Id$
 * @since 0.1
 */
public class BattleTest {
    @Test
    public void whenBattleTwoWarriors() {
        List<Soldier> allians = new ArrayList<>();
        allians.add(new MageOfHumans());
        allians.add(new ArcherOfHumans());
        allians.add(new ArcherOfElvis());
        allians.add(new ArcherOfHumans());
        allians.add(new WarriorOfElvis());
        allians.add(new WarriorOfElvis());
        allians.add(new WarriorOfHumans());
        allians.add(new WarriorOfHumans());
        List<Soldier> orda = new ArrayList<>();
        orda.add(new MageOfUndead());
        orda.add(new ArcherOrc());
        orda.add(new ArcherOfUndead());
        orda.add(new ArcherOfUndead());
        orda.add(new WarriorOfOrc());
        orda.add(new WarriorOfOrc());
        orda.add(new WarriorOfUndead());
        orda.add(new WarriorOfOrc());
        Game game = new Game();
        game.setAlians(allians);
        game.setOrda(orda);
        game.setGame();
      game.start();
        System.out.println(Game.builder);
    }
}
