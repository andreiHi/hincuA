package ru.job4j.io;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import static org.hamcrest.Matchers.containsInAnyOrder;

import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.junit.Assert.*;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 27.01.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class SearchTest {
    String path = System.getProperty("java.io.tmpdir"); //папка темп

    @Test
    public void test() throws IOException {
        System.out.println(path);
        List<String> expected = new ArrayList<>();
        String parent = path + "/parent";
        new File(parent).mkdirs();
        new File(parent + "/child1").mkdirs();
        new File(parent + "/child2").mkdirs();
        new File(parent + "/child1/child3").mkdirs();
        File file = new File(parent + "/child1/child3/test.txt");
        file.createNewFile();
        expected.add(file.getAbsolutePath());
        new File(parent + "/child1/child3/test.tx").createNewFile();
        File myFolder = new File(parent);
        File[] files1 = myFolder.listFiles();
        Search search = new Search();
        List<String> exts = new ArrayList<>();
        exts.add(".html");
        exts.add(".txt");
        System.getProperty("java.io.tmpdir");
        assertThat(search.files(parent, exts).
                stream().
                map(File::getAbsolutePath).toArray(), arrayContainingInAnyOrder(expected.toArray()));
    }
    @Test
    public void whenScanFilesThenExist() {
        List<String> listExtension = List.of("txt", "exe");
        String parent = System.getProperty("java.io.tmpdir") + "/Test/";
        File[] directory = new File[]{
                new File(parent + "Dir1/"),
                new File(parent + "Dir2/Folder1/Folder2/"),
                new File(parent + "Dir3/Folder1/Folder2/"),
                new File(parent + "Dir4/Folder1/"),
                new File(parent + "Dir5/Folder1/Folder2/"),
                new File(parent + "Dir6/Folder1/Folder2/Folder3/")};
        File[] expected = new File[]{
                new File(parent + "Dir1/file.txt"),
                new File(parent + "Dir2/file1234.exe"),
                new File(parent + "Dir2/Folder1/Folder2/file.exe"),
                new File(parent + "Dir2/Folder1/file123.exe"),
                new File(parent + "Dir3/Folder1/Folder2/file.txt"),
                new File(parent + "Dir4/Folder1/fileOne.exe"),
                new File(parent + "Dir4/Folder1/fileTwo.exe"),
                new File(parent + "Dir5/Folder1/Folder2/file.txt"),
                new File(parent + "Dir6/Folder1/Folder2/Folder3/file.exe")};
        Arrays.stream(directory).filter(folder -> !folder.exists()).forEach(File::mkdirs);
        Arrays.stream(expected).filter(file -> !file.exists()).forEach(file -> {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Search search = new Search();
        assertThat(search.files(parent, listExtension), containsInAnyOrder(expected));
    }
}
