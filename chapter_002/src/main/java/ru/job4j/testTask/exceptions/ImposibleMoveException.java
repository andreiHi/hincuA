package ru.job4j.testTask.exceptions;

/**
 * Неправильный ход.
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 09.09.17;
 * @version $Id$
 * @since 0.1
 */
public class ImposibleMoveException extends RuntimeException {
    public ImposibleMoveException(String s) {
        super(s);
    }
}
