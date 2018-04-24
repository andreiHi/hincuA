package ru.job4j.test;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 27.03.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class Test {
    private static final Logger LOG = LogManager.getLogger(Test.class);

    public static void main(String[] args) {
        File file = new File("C:\\projects\\hincuA\\chapter_11\\shopCar\\src\\main\\webapp\\uploadDir\\1657886478.jpg");
        try {
            Thumbnails.of(file)
                    .size(200, 200)
                    .toFile(Paths.get(file.getAbsolutePath().replace(".jpg", "-sml.jpg")).toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T extends Enum<T>> String getJson(Class<T> tClass) {
        return JSONArray.toJSONString(Arrays.stream(tClass.getEnumConstants()).map(Enum::name)
                .collect(Collectors.toList()));
//        for (Enum<T> tenum : tClass.getEnumConstants()) {
//            list.add(tenum.name());
//        }

    }
    public static <T extends Enum<T>> JSONArray getJson1(Class<T> tClass) {
        JSONArray array = new JSONArray();
        for (Enum<T> tenum : tClass.getEnumConstants()) {
            array.add(tenum.name());
        }
//        array.addAll(Arrays.stream(tClass.getEnumConstants()).map(Enum::name).collect(Collectors.toList()));
        return array;
    }
}
