package ru.job4j.tracker.input;

import ru.job4j.tracker.exceptions.MenuOutputException;

/**
 * .
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 08.09.17;
 * @version $Id$
 * @since 0.1
 */
public class ValidateInput implements Input {
    @Override
    public String ask(String question) {
        return null;
    }

    @Override
    public void writeMessage(String message) {

    }
    private Input input;

    public ValidateInput(Input input) {
        this.input = input;
    }

    /**
     * Метод с обработкой ошибок.
     * @param question вопрос.
     * @return ответ.
     */
    @Override
    public int ask(String question, int[] range) {
        boolean invalid = true;
        int value = 0;
        do {
            try {
                value = this.input.ask(question, range);
                invalid = false;
            } catch (MenuOutputException e) {
                System.out.println("Please select key from menu.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter validate data again.");
            }
        } while (invalid);
        return value;
    }
}
