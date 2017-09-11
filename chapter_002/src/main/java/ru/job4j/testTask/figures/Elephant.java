package ru.job4j.testTask.figures;

import ru.job4j.testTask.Cell;
import ru.job4j.testTask.Figure;
import ru.job4j.testTask.exceptions.ImposibleMoveException;

/**
 * .
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 11.09.17;
 * @version $Id$
 * @since 0.1
 */
public class Elephant extends Figure {
    /**
     * Конструктор.
     * @param name     название фигуры.
     * @param pozition позиция фигуры.
     */
    public Elephant(String name, Cell pozition) {
        super(name, pozition);
    }

    @Override
    public Cell[] way(Cell dist) throws ImposibleMoveException {
       Cell currentCell = null;
       for (int i = 1; i != 9; i++) {
           for (int j = 1; j != 9; j++) {
              Cell cell = new Cell(i, j);
               if (this.isOneCell(cell)) {
                   currentCell = cell;
                   break;
               }
           }
       }
       Cell[] away = new Cell[8];
       int pozition = 0;
        if (dist.getWidth() > currentCell.getWidth() && dist.getHeight() > currentCell.getHeight()) {
            for (int i = currentCell.getHeight() + 1; i < dist.getHeight() + 1; i++) {
                int j = currentCell.getWidth() +1;
                away[pozition] = new Cell(i, j);
                pozition++;
                j++;
            }

        }
        return away;
    }
}
