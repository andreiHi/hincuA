package ru.job4j.sql;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import ru.job4j.sql.items.Advert;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 25.12.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class Test {
    public static void main(String[] args) throws IOException {
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
        String d  = "сегодня, 20:54";
        Calendar cal = Calendar.getInstance();
        if (d.startsWith("сегодня")) {
            d = d.replaceAll("сегодня", new SimpleDateFormat("d MMM yy").format(cal.getTime()));
            System.out.println(d);
        }
        System.out.println(format.format(cal.getTime()));
       // LocalDateTime
    }


}
