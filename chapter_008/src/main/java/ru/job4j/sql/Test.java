package ru.job4j.sql;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.sql.items.Advert;


import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 25.12.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class Test {
    ArrayBlockingQueue<Advert>queue;
    public static void main(String[] args) throws IOException {
  Test t = new Test();
  t.scanAllAdvertFromSqlRu("http://www.sql.ru/forum/job-offers/2");

    }

    public void scanAllAdvertFromSqlRu(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            Elements element = doc.getElementsByAttributeValue("class", "forumtable");
            Elements tagAdverts = element.select("tr");
            int count = 0;
            for (int i = 4; i < tagAdverts.size(); i++) {
                Element node = tagAdverts.get(i);
                Advert advert = new Advert();
                count++;
                Elements refAndText = node.getElementsByAttributeValue("class", "postslisttopic");
                for (Element firstElement : refAndText) {
                    Element element1 = firstElement.child(0);
                    String urlItem = element1.attr("href");
                    advert.setUrl(urlItem);
                }
                Elements items = node.getElementsByAttributeValue("class", "altCol");
                Element dataNode = items.last();
                if (dataNode != null) {
                    String data = dataNode.text();
                    advert.setPublicationDate(data);
                }
                System.out.println(advert);
            }

            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
