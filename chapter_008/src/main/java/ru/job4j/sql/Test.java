package ru.job4j.sql;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import ru.job4j.sql.items.Advert;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 25.12.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class Test {
    public static void main(String[] args) throws IOException {
//        List<String> strings = new ArrayList<>();
//        strings.add("k1/sk1/ssk1");
//        strings.add("k2/sk2/ssk2");
//        Test t = new Test();
//        t.addNewItems(strings).forEach(System.out::println);
        Document doc = Jsoup.connect("http://www.sql.ru/forum/job-offers").get();
        Element link = doc.select("a").first();
        String relHref = link.attr("href"); // == "/"
        System.out.println(relHref);
        String absHref = link.attr("abs:href"); // "http://jsoup.org/"
        System.out.println(absHref);
        SimpleDateFormat format = new SimpleDateFormat("d MMM yy, HH:mm");
        System.out.println(format.format(new Date()));
        //19 дек 17, 10:30
        //25 дек 17, 22:32
    }
    public List<String> addNewItems(List<String> strings) {
        List<String> result = new ArrayList<>();
        for (Iterator<String> iterator = strings.iterator(); iterator.hasNext();) {
            String s = iterator.next();
            String[] str = s.split("/");
            String item = "";
            for (int i = 0; i < str.length - 1; i++) {
                item += str[i];
                if (!result.contains(item)) {
                    result.add(item);
                    item += "/";
                }
            }
            result.add(s);
        }
        return result;
    }

}
