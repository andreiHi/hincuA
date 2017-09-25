package ru.job4j.litle.worldofwarcraft.solgers;

import java.util.List;

/**
 * Интерфейс атаки.
 * @author Hincu Andrei (andreih1981@gmail.com)on 25.09.2017.
 * @version $Id$.
 * @since 0.1.
 */
public interface Attack {

    /**
     * Атака.
     */
    List<Soldier> attack();
}
