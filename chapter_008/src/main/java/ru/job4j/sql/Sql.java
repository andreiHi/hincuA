package ru.job4j.sql;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.sql.items.Advert;
import ru.job4j.sql.items.Author;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 22.12.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class Sql {
    private static final String URL = "http://www.sql.ru/forum/job-offers/1";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("d MMM yy, HH:mm");
    private static final SimpleDateFormat DATE_PREPARE = new SimpleDateFormat("d MMM yy");

    public static void main(String[] args) {
        //DB db = new DB();
        Sql sql = new Sql();
        sql.scanAdvertFromSqlRu(URL);

    }


    public List<Advert> scanAdvertFromSqlRu(String urlSite) {
        List<Advert> result = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(urlSite).get();
            Elements element = doc.getElementsByAttributeValue("class", "forumtable");
            Elements tagAdverts = element.select("tr");
            int count = 0;

            for (Element node : tagAdverts) {
                Advert advert = null;
                System.out.println(count++);
                Elements refAndText = node.getElementsByAttributeValue("class", "postslisttopic");
                for (Element firstElement : refAndText) {
                    Element element1 = firstElement.child(0);
                    String url = element1.attr("href");
                    String text = getTextFromUrl(url);
                    if (!validAdvert(text)) {
                        break;
                    }
                    advert = new Advert();
                    String title = element1.text();
                    advert.setUrl(url);
                    advert.setTitle(title);
                    advert.setText(text);
                }
                if (advert != null) {
                    Elements authors = node.getElementsByAttributeValue("class", "altCol");
                    Element author = authors.first();
                    if (author != null) {
                        Author aut = new Author();
                        String url = author.child(0).attr("href");
                        String name = author.text();
                        aut.setName(name);
                        aut.setUrl(url);
                        advert.setAuthor(aut);
                        String data = authors.last().text();
                        advert.setDate(prepareDate(data));
                    }
                    System.out.println(advert);
                }

            }
            Elements elements = doc.getElementsByAttributeValue("class", "sort_options");


        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private boolean validAdvert(String text) {
        boolean ok = false;
        if (text.contains("java") || text.contains("Java") || text.contains("JavaSE/EE") || text.contains("JavaEE")) {
            ok = !text.contains("Java Script") && !text.contains("JavaScript");
        }
    return ok;
    }

    private Calendar prepareDate(String data) {
        Calendar calendar = Calendar.getInstance();
        if (data != null) {
            final String today = "сегодня";
            final String yesterday = "вчера";
            if (data.startsWith(today)) {
                data = data.replaceAll(today, DATE_PREPARE.format(calendar.getTime()));
            }
            if (data.startsWith(yesterday)) {
                calendar.add(Calendar.DATE, -1);
                data = data.replaceAll(yesterday, DATE_PREPARE.format(calendar.getTime()));
            }
            try {
                calendar.setTime(DATE_FORMAT.parse(data));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return calendar;
    }
    private String getTextFromUrl(String url) {
        String text = "";
        try {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.getElementsByAttributeValue("class", "msgBody");
            if (elements.size() > 1) {
                Element element = elements.get(1);
                text = element.text();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }
}
