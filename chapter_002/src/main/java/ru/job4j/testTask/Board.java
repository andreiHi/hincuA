package ru.job4j.testTask;

import ru.job4j.testTask.exceptions.FigureNotFoundException;
import ru.job4j.testTask.exceptions.ImposibleMoveException;
import ru.job4j.testTask.exceptions.OccupiedWayException;

/**
 * Доска .
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 09.09.17;
 * @version $Id$
 * @since 0.1
 */
public class Board {
    private Figure[] figures = new Figure[32];
    private int pozition = 0;

    public Board(Figure[] figures) {
        this.figures = figures;
    }

    public void putFigure(Figure figure) {
        this.figures[pozition++] =figure;
    }

    boolean move(Cell source, Cell dist) throws ImposibleMoveException, OccupiedWayException, FigureNotFoundException {
        Figure figure = null;
        try {

        }catch (FigureNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (OccupiedWayException t) {

        } catch (ImposibleMoveException e) {

        }
        return false;
    }
}
