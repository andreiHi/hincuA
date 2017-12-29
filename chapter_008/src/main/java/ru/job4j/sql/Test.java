package ru.job4j.sql;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
//        Document doc = Jsoup.connect("http://www.sql.ru/forum/job-offers").get();
//        Element link = doc.select("a").first();
//        String relHref = link.attr("href"); // == "/"
//        System.out.println(relHref);
//        String absHref = link.attr("abs:href"); // "http://jsoup.org/"
//        System.out.println(absHref);
//        SimpleDateFormat format = new SimpleDateFormat("d MMM yy, HH:mm");
//        System.out.println(format.format(new Date()));
//        //19 дек 17, 10:30
//        //25 дек 17, 22:32
//        String d  = "сегодня, 20:54";
//        Calendar cal = Calendar.getInstance();
//        if (d.startsWith("сегодня")) {
//            d = d.replaceAll("сегодня", new SimpleDateFormat("d MMM yy").format(cal.getTime()));
//            System.out.println(d);
//        }
//        System.out.println(format.format(cal.getTime()));
        // LocalDateTimeDo
        String today = "Я представляю компанию \"АИСА ИТ-СЕРВИС\". Наша компания-интегратор занимается разработкой и внедрением автоматизированных информационных систем. В связи с появлением новых проектов от наших заказчиков, в нашей компании открыта вакансия Junior Java developer на ключевом проекте компании. Требования: Обязательно: java (принципы ООП, знание java SE, мультипоточность) JDBC (подключение и работа с базами данных) RESTful-сервисы Системы управления версиями (SVN, Git) SQL (умение составлять sql-запросы) Apache Maven (сборка проектов, запуск, зависимости) Желательно: Xsd, Wsdl, SOAP-сервисы spring Framework javascript фреймворки (jQuery, bootstrap, AngularJS) Linux (в любом случае придется на него пересаживаться) Условия работы: - Работа в офисе, рядом с м.Чернышевская. - Оформление по ТК, больничные/отпускные/\"белая\"зарплата без задержек 2 раза в месяц. - Заработная плата определяется руководителем в ходе собеседования, готовы рассматривать Ваши пожелания. - Навыки решения реальных боевых задач. - Возможность быстрого карьерного роста в перспективной IT компании, работа в молодом и дружном коллективе. - Получение новых знаний у коллег с большим опытом работы в данной сфере, возможность знакомства на практике с широким стеком современных технологий. Обязанности: - Разработка проектов с нуля в команде; - Поддержка и развитие существующих проектов. Контакты: Екатерина, ekaterina.fedorova@aisa.ru'";
        System.out.println(today.length());

    }

    private void validAdvert(String text) {

        Pattern pattern =  Pattern.compile("[j,J]ava\\s?(?=SE/EE|EE|SE)?(?!\\s?[s,S]cript)");

        Matcher  matcher = pattern.matcher(text);
        System.out.println(matcher.find());


    }
}
