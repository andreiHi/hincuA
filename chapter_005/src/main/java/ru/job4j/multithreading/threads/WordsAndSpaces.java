package ru.job4j.multithreading.threads;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 04.11.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class WordsAndSpaces implements Runnable {
    private static String text = "— Eh bien, mon prince. Gênes et Lucques ne sont plus que des apanages, des поместья, de la famille Buonaparte. Non, je vous préviens que si vous ne me dites"
            + " pas que nous avons la guerre, si vous vous permettez encore de pallier toutes les infamies, toutes les atrocités de cet Antichrist (ma parole, j'y crois) — je ne vous connais plus, vous "
            + "n'êtes plus mon ami, vous n'êtes plus мой верный раб, comme vous dites 1. Ну, здравствуйте, здравствуйте. Je vois que je vous fais peur 2, садитесь и рассказывайте."
            + "Так говорила в июле 1805 года известная Анна Павловна Шерер, фрейлина и приближенная императрицы Марии Феодоровны, встречая важного и чиновного князя Василия, первого приехавшего на ее вечер."
            + " Анна Павловна кашляла несколько дней, у нее был грипп, как она говорила (грипп был тогда новое слово, употреблявшееся только редкими). В записочках, разосланных утром с красным лакеем, было"
            + " написано без различия во всех:"
            + "«Si vous n'avez rien de mieux à faire, Monsieur le comte (или mon prince), et si la perspective de passer la soirée chez une pauvre malade ne vous effraye pas trop,"
            + "— Dieu, quelle virulente sortie! 4 — отвечал, нисколько не смутясь такою встречей, вошедший князь, в придворном, шитом мундире, в чулках, башмаках и звездах, с светлым выражением плоского лица."
            +"— Eh bien, mon prince. Gênes et Lucques ne sont plus que des apanages, des поместья, de la famille Buonaparte. Non, je vous préviens que si vous ne me dites"
            + " pas que nous avons la guerre, si vous vous permettez encore de pallier toutes les infamies, toutes les atrocités de cet Antichrist (ma parole, j'y crois) — je ne vous connais plus, vous "
            + "n'êtes plus mon ami, vous n'êtes plus мой верный раб, comme vous dites 1. Ну, здравствуйте, здравствуйте. Je vois que je vous fais peur 2, садитесь и рассказывайте."
            + "Так говорила в июле 1805 года известная Анна Павловна Шерер, фрейлина и приближенная императрицы Марии Феодоровны, встречая важного и чиновного князя Василия, первого приехавшего на ее вечер."
            + " Анна Павловна кашляла несколько дней, у нее был грипп, как она говорила (грипп был тогда новое слово, употреблявшееся только редкими). В записочках, разосланных утром с красным лакеем, было"
            + " написано без различия во всех:"
            + "«Si vous n'avez rien de mieux à faire, Monsieur le comte (или mon prince), et si la perspective de passer la soirée chez une pauvre malade ne vous effraye pas trop,"
            + "— Dieu, quelle virulente sortie! 4 — отвечал, нисколько не смутясь такою встречей, вошедший князь, в придворном, шитом мундире, в чулках, башмаках и звездах, с светлым выражением плоского лица.";

    /**
     *  Метод запускает два потока.
     * @throws InterruptedException
     */
    public void wordsAndSpaces() throws InterruptedException {
        System.out.println("Начало работы метода");
        String[] words = text.split(" ");
        char[] chars = text.toCharArray();
        Thread thread = new Thread() {
            int count = 0;
            @Override
            public void run() {
                System.out.println("Считаем слова.");
                for (String s : words) {
                    count++;
                }
                System.out.println(String.format("Слов %s", count));
            }
        };
        thread.start();
        thread.join();
        Thread spaces = new Thread() {
            int count = 0;
            @Override
            public void run() {
                System.out.println("Считаем пробелы.");
                for (Character c : chars) {
                    if (Character.isWhitespace(c)) {
                        count++;
                    }
                }
                System.out.println(String.format("Пробелов %s", count));
            }
        };
        spaces.start();
        spaces.join();
    }

    public static void main(String[] args) {
        WordsAndSpaces word = new WordsAndSpaces();
        System.out.println("Начало работы метода");
        String[] words = text.split(" ");
        new Thread() {
            int count = 0;
            @Override
            public void run() {
                System.out.println("Считаем слова.");
                for (String s : words) {
                    count++;
                }
                System.out.println(String.format("Слов %s", count));
            }
        }.start();
        Thread spaces = new Thread(word);
        spaces.start();
    }

    @Override
    public void run() {
        int count = 0;
        char[]chars = text.toCharArray();
        System.out.println("Считаем пробелы.");
        for (Character c : chars) {
            if (Character.isWhitespace(c)) {
                count++;
            }
        }
        System.out.println(String.format("Пробелов %s", count));
    }
}

