package ru.job4j.io;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 27.01.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class Search {
    private static final Logger LOG = LogManager.getLogger(Search.class);
    private LinkedList<File> files = new LinkedList<>();
    private Queue<String> folders = new LinkedList<>();
    public List<File> files(String parent, List<String> exts) {
        OnlyExt onlyExt = new OnlyExt(exts);
        folders.add(parent);
        while (!folders.isEmpty()) {
            String path = folders.poll();
            File tmp = new File(path);
            if (tmp.isDirectory()) {
                for (File f : tmp.listFiles(onlyExt)) {
                    folders.offer(f.getAbsolutePath());
                }
            } else {
                //onlyExt.accept()
                files.add(tmp);
            }
        }
        return files;
    }

    private class OnlyExt implements FilenameFilter {
        private List<String> exts;

        public OnlyExt(List<String> exts) {
            this.exts = exts;
        }

        public boolean accept(File dir, String name) {
            File file = new File(dir + "/" + name);
            return exts.stream().anyMatch(s -> file.isDirectory() || name.endsWith(s));
        }
    }

    public List<File> listOfOthers(String path, List<String> ex) {
        return new ArrayList<>();
    }
}
