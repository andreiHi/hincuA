package ru.job4j.sql;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.sql.database.DB;
import ru.job4j.sql.items.Advert;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 25.12.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class Test {
    public static void main(String[] args) throws IOException {
    Sql sql = new Sql(new DB());
    String a = sql.getNextPageUrl("http://www.sql.ru/forum/job-offers/642");
        System.out.println(a == null);

    }

    private void validAdvert(String text) {

        Pattern pattern =  Pattern.compile("[j,J]ava\\s?(?=SE/EE|EE|SE)?(?!\\s?[s,S]cript)");

        Matcher  matcher = pattern.matcher(text);
        System.out.println(matcher.find());


    }
}
