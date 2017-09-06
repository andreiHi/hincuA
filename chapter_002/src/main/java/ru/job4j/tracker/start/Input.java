package ru.job4j.tracker.start;

/**
 * Input.
 * @author Hincu Andrei (andreih1981@gmail.com) by 06.09.17;
 * @version $Id$
 * @since 0.1
 */
public interface Input {
    /**
     * Метод для общения с пользователем.
     * @param question вопрос пользователю.
     * @return ответ пользователя.
     */
    String ask(String question);

    /**
     * Вывод сообшения.
     * @param message сообщение.
     */
    void writeMessage(String message);
}
