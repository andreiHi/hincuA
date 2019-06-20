package ru.job4j.io;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 10.04.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class Arhiver {

    private void zipDirectory(String directory, String exclude, String output) {
        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(output))) {
            File folderToZip = new File(directory);
            zipFiles(folderToZip, exclude, zipOut);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void zipFiles(File folderToZip, String exclude, ZipOutputStream zos) throws IOException {
        Queue<File> queue = new LinkedList<>();
        queue.offer(folderToZip);
        while (!queue.isEmpty()) {
            File[] files = queue.poll().listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        queue.offer(file);
                        int rootLength = folderToZip.getPath().length() + File.separator.length();
                        zos.putNextEntry(new ZipEntry(file.getPath().substring(rootLength) + "/"));
                        zos.closeEntry();
                    } else if (!file.getName().contains(exclude)) {
                        try (FileInputStream fis = new FileInputStream(file)) {
                            int rootLength = folderToZip.getPath().length() + File.separator.length();
                            zos.putNextEntry(new ZipEntry(file.getPath().substring(rootLength)));
                            byte[] data = new byte[1024];
                            int length;
                            while ((length = fis.read(data)) >= 0) {
                                zos.write(data, 0, length);
                            }
                            zos.closeEntry();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private int findKey(String[] array, String key) {
        int result = 0;
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                if (array[i].contains(key)) {
                    result = i + 1;
                    break;
                }
            }
        }
        return result;
    }

    private String directory(String[] dir) {
        String result = null;
        if (dir != null) {
            result = dir[findKey(dir, "-d")];
        }
        return result;
    }

    private String exclude(String[] ex) {
        String result = null;
        if (ex != null) {
            result = ex[findKey(ex, "-e")];
        }
        return result;
    }

    private String output(String[] out) {
        String result = null;
        if (out != null) {
            result = out[findKey(out, "-o")];
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        Arhiver arhiver = new Arhiver();
        final File file = new File("config");
        final Package[] definedPackages = Arhiver.class.getClassLoader().getDefinedPackages();
        for (Package definedPackage : definedPackages) {
            System.out.println(definedPackage);
        }
        System.out.println(file.exists());
        System.out.println(file.getPath());
     //   arhiver.Zip("D:\\projects\\hincuA\\chapter_010\\config", "config.zip");
        //Получение пути файла относительно текущей директории
        //   final Path relativize = source.toPath().relativize(file.toPath());
    }
    public void output(final String projectName, final File path, final List<File> files) throws IOException {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(projectName))) {
            for (File f : files) {
                zout.putNextEntry(new ZipEntry(f.getPath().substring(f.getPath().indexOf(path.getName()))));
                try (InputStream is = new FileInputStream(f)) {
                    int value;
                    while ((value = is.read()) != -1) {
                        zout.write(value);
                    }
                }
                zout.closeEntry();
            }
        }
    }
    private Search search = new Search();
    private byte[] buffer = new byte[4096];

    private void form(ZipOutputStream zos, File source, List<String> expansions) throws IOException {
        List<File> list = search.listOfOthers(source.getPath(), expansions);
        for (File file : list) {
            if (file.isDirectory()) {
                form(zos, file, expansions);
                continue;
            }
            try (FileInputStream fis = new FileInputStream(file)) {
                final Path relation = source.toPath().relativize(file.toPath());
                zos.putNextEntry(new ZipEntry(relation.toString()));
                int length;
                while ((length = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, length);
                }
                zos.closeEntry();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
