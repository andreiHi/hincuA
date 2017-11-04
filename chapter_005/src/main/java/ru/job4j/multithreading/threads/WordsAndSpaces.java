package ru.job4j.multithreading.threads;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 04.11.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class WordsAndSpaces implements Runnable {
    private String text ="— Eh bien, mon prince. Gênes et Lucques ne sont plus que des apanages," +
            " des поместья, de la famille Buonaparte. Non, je vous préviens que si vous ne me dites" +
            " pas que nous avons la guerre, si vous vous permettez encore de pallier toutes les infamies," +
            " toutes les atrocités de cet Antichrist (ma parole, j'y crois) — je ne vous connais plus, vous " +
            "n'êtes plus mon ami, vous n'êtes plus мой верный раб, comme vous dites 1. Ну, здравствуйте, здравствуйте. Je vois que je vous fais peur 2, садитесь и рассказывайте.\n" +
            "Так говорила в июле 1805 года известная Анна Павловна Шерер, фрейлина и приближенная императрицы Марии Феодоровны, встречая важного и чиновного князя Василия, первого приехавшего на ее вечер. Анна Павловна кашляла несколько дней, у нее был грипп, как она говорила (грипп был тогда новое слово, употреблявшееся только редкими). В записочках, разосланных утром с красным лакеем, было написано без различия во всех:\n" +
            "«Si vous n'avez rien de mieux à faire, Monsieur le comte (или mon prince), et si la perspective de passer la soirée chez une pauvre malade ne vous effraye pas trop, je serai charmée de vous voir chez moi entre 7 et 10 heures. Annette Scherer» 3.\n" +
            "— Dieu, quelle virulente sortie! 4 — отвечал, нисколько не смутясь такою встречей, вошедший князь, в придворном, шитом мундире, в чулках, башмаках и звездах, с светлым выражением плоского лица.";
    public void wordsAndSpaces() {
        String[] array = text.split(" ");
        char[]chars = text.toCharArray();
        Thread thread = new Thread(){
            int count = 0;
            @Override
            public void run() {


                System.out.println(count);
                System.out.println("Hello");
            }
        };
        thread.start();
    }

    @Override
    public void run() {

    }
}
