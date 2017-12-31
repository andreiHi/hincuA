package ru.job4j.sql.items;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.sql.database.DB;

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
    private Calendar dateLastUptdate;

    public PageParser(ArrayBlockingQueue<Advert> queue, DB db) {
        this.queue = queue;
        this.db = db;
    }

    @Override
    public void run() {
        db.createTables();
        long lastTimeFromBd = db.getLastTimeOfUpdate();
        if (lastTimeFromBd == 0) {
            String url = URL;
            do {
                scanAllAdvertFromSqlRu(url, queue);
                url = getNextPageUrl(url);
            } while (!url.endsWith("10"));

        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(lastTimeFromBd);
            this.dateLastUptdate = calendar;
        }
    }
    public void scanAllAdvertFromSqlRu(String url, ArrayBlockingQueue<Advert> queue) {
        try {
            Document doc = Jsoup.connect(url).get();
            Elements element = doc.getElementsByAttributeValue("class", "forumtable");
            Elements tagAdverts = element.select("tr");
            int count = 0;
            for (int i = 4; i < tagAdverts.size(); i++) {
                Element node = tagAdverts.get(i);
                Advert adverd = new Advert();
                count++;
                Elements refAndText = node.getElementsByAttributeValue("class", "postslisttopic");
                for (Element firstElement : refAndText) {
                    Element element1 = firstElement.child(0);
                    String urlItem = element1.attr("href");
                    adverd.setUrl(urlItem);
                }
                Elements items = node.getElementsByAttributeValue("class", "altCol");
//                Element author = items.first();
//                Author aut = new Author();
//                String url1 = author.child(0).attr("href");
//                String name = author.text();
//                aut.setName(name);
//                aut.setUrl(url1);
//                adverd.setAuthor(aut);
                String data = items.last().text();
                adverd.setPublicationDate(data);
                queue.put(adverd);
            }

            System.out.println(count);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public String getNextPageUrl(String url) {
        Document doc = null;
        String nextPageUrl = "";
        try {
            doc = Jsoup.connect(url).get();
            Elements elements = doc.getElementsByAttributeValue("class", "sort_options");
            nextPageUrl = elements.last().getElementsByTag("b").next().attr("href");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nextPageUrl;
    }
}
