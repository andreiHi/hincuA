package ru.job4j.io.manager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 27.01.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class FileManager implements Manager {
    private static final Logger LOG = LogManager.getLogger(FileManager.class);
    private String currentCatalog;
    private final String root;

    public FileManager(String root) {
        this.currentCatalog = root;
        this.root = root;
    }

    @Override
    public List<String> getRoot() {
        List<String> result = new ArrayList<>();
        File files = new File(currentCatalog);
        Arrays.stream(Objects.requireNonNull(files.listFiles()))
                .map(File::getName)
                .forEach(result::add);
        return result;
    }

    @Override
    public String getAtRoot(final String catalog) {
        String result;
        String temp = this.currentCatalog + "\\" + catalog;
        if (new File(temp).exists()) {
            this.currentCatalog = temp;
            result = this.currentCatalog;
        } else {
            result = "this folder doesn't exist";
        }
        return result;
    }

    @Override
    public String getParent() {
        this.currentCatalog = this.root;
        return this.currentCatalog;
    }

    @Override
    public String uploadFile(String fileName, String path) {
        File dest = new File(this.currentCatalog + File.separator + fileName);
        File source = new File(path + File.separator + fileName);
        return download(source, dest);
    }

    @Override
    public String downloadFile(String fileName, String path) {
        File source = new File(this.currentCatalog + File.separator + fileName);
        File dest = new File(path + File.separator + fileName);
        return download(source, dest);
    }

    private String download(File source, File dest) {
        try {
            Files.copy(source.toPath(), dest.toPath());
        } catch (IOException e) {
            return "mistake";
        }
        return "success";
    }
}
