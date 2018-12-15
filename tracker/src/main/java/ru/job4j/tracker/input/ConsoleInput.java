package ru.job4j.tracker.input;

import ru.job4j.tracker.exceptions.MenuOutputException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 *ConsoleInput.
 * @author Hincu Andrei (andreih1981@gmail.com) by 05.09.17;
 * @version $Id$
 * @since 0.1
 */
public class ConsoleInput implements Input {
    /**
     * Поток чтения.
     */
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    /**
     * Сканер.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Задаем вопрос , читаем ответ.
     * @param question вопрос.
     * @return ответ пользователя.
     */
    public String ask(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }

    /**
     * Вывод сообщения пользователю.
     * @param message сообщение.
     */
    public void writeMessage(String message) {
        System.out.println(message);
    }

    /**
     * Перегруженный метод ask.
     * @param question вопрос пользователю.
     * @param range правильный набор ответов.
     * @return ключь.
     */
    @Override
    public int ask(String question, int[] range) {
       int key = Integer.parseInt(this.ask(question));
       boolean exist = false;
        for (int value : range) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            throw new MenuOutputException("Out of menu range!");
        }
        return key;
    }
}
