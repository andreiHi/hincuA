package ru.job4j.sql;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import ru.job4j.sql.database.DB;
import ru.job4j.sql.items.Advert;
import ru.job4j.sql.items.Author;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 31.12.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class AdvertScanner implements Runnable {
    private ArrayBlockingQueue<Advert> adverts;
    private DB db;

    public AdvertScanner(ArrayBlockingQueue<Advert> adverts, DB db) {
        this.adverts = adverts;
        this.db = db;
    }

    @Override
    public void run() {
        int count = 0;
        do {
            try {
                Advert advert = adverts.poll(1000, TimeUnit.MILLISECONDS);
                String text;
                Document doc = Jsoup.connect(advert.getUrl()).get();
                Elements elements = doc.getElementsByAttributeValue("class", "msgTable");
                Elements element = elements.first().getElementsByAttributeValue("class", "msgBody");
                text =  element.last().text();
                //проверка на валидность текста
                if (Pattern.compile("[j,J]ava\\s?(?=SE/EE|SE|EE)?(?!\\s?[s,S]cript)").matcher(text).find()) {
                    advert.setText(text);
                    Elements elemData = elements.first().getElementsByAttributeValue("class", "msgFooter");
                    //получение даты создания обьявления
                    String data = elemData.text().split("\\[")[0].trim();
                    advert.setDate(Advert.prepareDate(data).getTimeInMillis());
                    advert.setTitle(elements.first().getElementsByAttributeValue("class", "messageHeader").first().text());
                    //получение автора обьявления и его url.
                    String urlAuthor = element.first().getElementsByTag("a").attr("href");
                    String nameAuthor = element.first().getElementsByTag("a").text();
                    Author author = new Author();
                    author.setUrl(urlAuthor);
                    author.setName(nameAuthor);
                    advert.setAuthor(author);
                    System.out.println(advert);
                    db.addNewAdvert(advert);
                    System.out.println(count++);
                }
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        } while (!adverts.isEmpty());
    }
}
