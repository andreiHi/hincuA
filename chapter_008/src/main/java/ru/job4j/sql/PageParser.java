package ru.job4j.sql;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.sql.database.DB;
import ru.job4j.sql.items.Advert;

import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 30.12.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class PageParser implements Runnable {
    private static final String URL = "http://www.sql.ru/forum/job-offers";
    private ArrayBlockingQueue<Advert> queue;
    private DB db;

    public PageParser(ArrayBlockingQueue<Advert> queue, DB db) {
        this.queue = queue;
        this.db = db;
    }

    @Override
    public void run() {
        Calendar calendar = Calendar.getInstance();
        db.createTables();
        long lastTimeFromBd = db.getLastTimeOfUpdate();
        if (lastTimeFromBd == 0) {
            calendar.set(2017, Calendar.JANUARY, 0, 0, 0);
        } else {
            calendar.setTimeInMillis(lastTimeFromBd);
        }
        scanAllAdvertFromSqlRu(URL, queue, calendar);
    }


    public void scanAllAdvertFromSqlRu(String url, ArrayBlockingQueue<Advert> queue, Calendar calendar) {
        long endTime = calendar.getTimeInMillis();
        try {
            Document doc = Jsoup.connect(url).get();
            Elements element = doc.getElementsByAttributeValue("class", "forumtable");
            Elements tagAdverts = element.select("tr");
            for (int i = 4; i < tagAdverts.size(); i++) {
                Element node = tagAdverts.get(i);
                Elements refAndText = node.getElementsByAttributeValue("class", "postslisttopic");
                Elements elemData = node.getElementsByAttributeValue("class", "altCol");
                String data = elemData.last().text();
                long dataMils = Advert.prepareDate(data).getTimeInMillis();
                if (dataMils == endTime || dataMils < endTime) {
                    return;
                }
                Advert advert = new Advert();
                advert.setPublicationDate(dataMils);
                String urlItem = refAndText.first().getElementsByTag("a").attr("href");
                advert.setUrl(urlItem);
                queue.put(advert);
            }
            Elements elements = doc.getElementsByAttributeValue("class", "sort_options");
            String nextPageUrl = elements.last().getElementsByTag("b").next().attr("href");
            scanAllAdvertFromSqlRu(nextPageUrl, queue, calendar);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
