package ru.job4j.multithreading.threads;

import java.io.*;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 04.11.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class WordsAndSpaces implements Runnable {
    /**
     * Хранилище текста.
     */
    private static StringBuilder text = new StringBuilder();

    /**
     * Читаем файл и сохраняем в хранилище.
     * @return текст.
     * @throws IOException ошибка чтения.
     */
    public StringBuilder readText() throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(new File("C://projects//hincuA//chapter_005//king")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (reader.ready()) {
            sb.append(reader.readLine()).append(System.lineSeparator());
        }
        return sb;
    }
    /**
     *  Метод запускает два потока.
     * @throws InterruptedException ошибка прерывания.
     */
    public void wordsAndSpaces() throws IOException {
        text = readText();
        System.out.println("Начало работы метода");
        String[] word = text.toString().split(" ");
        char[] chars = text.toString().toCharArray();
        Thread countWords = new Thread() {
            int count = 0;
            @Override
            public void run() {
                try {
                    if (!isInterrupted()) {
                        System.out.println("Считаем слова.");
                        for (String s : word) {
                            count++;
                        }
                        System.out.println(String.format("Слов %s", count));
                    } else {
                        throw new InterruptedException();
                    }
                } catch (InterruptedException e) {
                    System.out.println("Расчеты колличества слов прерваны.");;
                }
            }
        };
        Thread countSpaces = new Thread() {
            int count = 0;
            @Override
            public void run() {
                try {
                    if (!isInterrupted()) {

                        System.out.println("Считаем пробелы.");
                        for (Character c : chars) {
                            if (Character.isWhitespace(c)) {
                                count++;
                            }
                        }
                        System.out.println(String.format("Пробелов %s", count));
                    } else {
                        throw new InterruptedException();
                    }
                } catch (InterruptedException e) {
                    System.out.println("Расчеты колличества пробелов прерваны");
                }
            }
        };


        try {
            countSpaces.start();
            countSpaces.join();
            countWords.start();
            countWords.join();
            Thread.sleep(1000);
            countSpaces.interrupt();
            countWords.interrupt();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Завершение работы метода.");
    }

    public static void main(String[] args) throws IOException {
        WordsAndSpaces word = new WordsAndSpaces();
        System.out.println("Начало работы метода");
        text = word.readText();
        String[] words = text.toString().split(" ");
        Thread thread = new Thread() {
            int count = 0;
            @Override
            public void run() {
                try {
                    if (!interrupted()) {
                        System.out.println("Считаем слова.");
                        for (String s : words) {
                            count++;
                        }
                        System.out.println(String.format("Слов %s", count));
                    } else {
                        throw new InterruptedException();
                    }
                } catch (InterruptedException e) {
                    System.out.println("Расчеты колличества слов прерваны.");
                }

            }
        };
        thread.start();
        Thread spaces = new Thread(word);
        spaces.start();
        try {
            thread.join();
            spaces.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
        spaces.interrupt();

        System.out.println("Завершение метода.");

    }

    @Override
    public void run() {

        try {
            if (!Thread.interrupted()) {
                int count = 0;
                char[] chars = text.toString().toCharArray();
                System.out.println("Считаем пробелы.");
                for (Character c : chars) {
                    if (Thread.interrupted()) {
                        break;
                    }
                    if (Character.isWhitespace(c)) {
                        count++;
                    }
                }
                System.out.println(String.format("Пробелов %s", count));
            } else {
                throw new  InterruptedException();
            }
        } catch (InterruptedException e) {
            System.out.println("Расчеты колличества пробелов прерваны");
        }

    }

}

