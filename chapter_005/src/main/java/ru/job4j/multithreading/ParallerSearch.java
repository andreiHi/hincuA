package ru.job4j.multithreading;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 10.11.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class ParallerSearch {
    private List<String> result = new ArrayList<>();
    private final String root;
    private final String text;
    private final List<String> exts;
    Thread searchFiles = new Thread(new Runnable() {
        @Override
        public void run() {

        }
    });
    Thread searchText = new Thread(new Runnable() {
        @Override
        public void run() {

        }
    });

    public ParallerSearch(String root, String text, List<String> exts) {
        this.root = root;
        this.text = text;
        this.exts = exts;
    }

    public static void main(String[] args) {

    }
}
