package ru.job4j.testTask;

import ru.job4j.testTask.exceptions.ImposibleMoveException;


/**
 * Класс описывает модель фигуры.
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 09.09.17;
 * @version $Id$
 * @since 0.1
 */
public abstract class Figure {
    private final Cell pozition;
    private String name;

    /**
     * Конструктор.
     * @param pozition позиция фигуры.
     * @param name     название фигуры.
     */
    public Figure(String name, Cell pozition) {
        this.pozition = pozition;
        this.name = name;
    }

    /**
     * Метод описывает движение фигуры.
     * @param dist точка куда хотели бы пойти.
     * @return массив ячеек с ходами.
     * @throws ImposibleMoveException невозможность хода.
     */
    public abstract Cell[] way(Cell dist) throws ImposibleMoveException;

    /**
     * метод костыль на геттер.
     * @param cell клетка кот нужно проверить.
     * @return тут или не тут стоит наша фигура.
     */
    public boolean isOneCell(Cell cell) {
        return  cell.equals(pozition);
    }

}
