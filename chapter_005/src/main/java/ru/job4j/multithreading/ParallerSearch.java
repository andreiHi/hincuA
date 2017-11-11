package ru.job4j.multithreading;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 10.11.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class ParallerSearch {
    private List<String> result = new ArrayList<>();
    private Queue<File> files = new ArrayDeque<>();
    private final String root;
    private final String text;
    private final List<String> exts;
    private Thread searchFiles = new Thread(new Runnable() {
        @Override
        public void run() {
            File file = new File(root);
            search(file);
        }
    });
    private Thread searchText = new Thread(new Runnable() {
        @Override
        public void run() {

            while (searchFiles.isAlive() || !files.isEmpty()) {
                File file = files.poll();
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    boolean found = false;
                    while (reader.ready()) {
                        String s = reader.readLine();
                        if (s.contains(text)) {
                            found = true;
                            break;
                        }
                    }
                    if (found) {
                        result.add(file.getPath());
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    public ParallerSearch(String root, String text, List<String> exts) {
        this.root = root;
        this.text = text;
        this.exts = exts;
    }

    public static void main(String[] args) {
        String root = "/home/andrei/cod";
        String text = "hello world";
        List<String> exts = Arrays.asList("txt","log");
        ParallerSearch parallerSearch = new ParallerSearch(root, text, exts);
        parallerSearch.searchFiles.start();
        parallerSearch.searchText.start();
        while (parallerSearch.searchText.isAlive()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(parallerSearch.result);
    }

    private void search(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                if (f.isDirectory()) {
                    search(f);
                } else if (f.isFile()) {
                    String name = f.getName();
                    if (name.contains("\\.")) {
                        name = name.split("\\.")[1];
                        if (exts.contains(name)) {
                            this.files.add(f);
                        }
                    }
                }
            }
        }
    }
}
