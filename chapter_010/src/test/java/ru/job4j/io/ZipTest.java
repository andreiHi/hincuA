package ru.job4j.io;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 10.04.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class ZipTest {

    @Test
    public void name() throws IOException {
         String property = System.getProperty("java.io.tmpdir");
        if (!property.endsWith("\\")) {
            property += File.separator;
        }
        String out = property + "archive.zip";
        final File file = new File("config");
        final String string = "java -jar pack.jar -d " + file.getAbsolutePath() + " -e *.txt -o " + out;
        System.out.println(out);
        final String[] split = string.split(" ");
        new Zip(split).archive();
        File zip = new File(out);
        Path expectedZipFile = Paths.get(zip.getAbsolutePath());
        assertTrue(Files.exists(expectedZipFile));
    }

}
























