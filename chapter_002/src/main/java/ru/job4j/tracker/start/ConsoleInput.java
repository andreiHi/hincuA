package ru.job4j.tracker.start;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *ConsoleInput.
 * @author Hincu Andrei (andreih1981@gmail.com) by 05.09.17;
 * @version $Id$
 * @since 0.1
 */
public class ConsoleInput implements Input {
    /**
     * поток чтения.
     */
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Задаем вопрос , читаем ответ.
     * @param question вопрос.
     * @return ответ пользователя.
     */
    public String ask(String question) {
        System.out.println(question);
        String line = "";
        try {
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }
}
