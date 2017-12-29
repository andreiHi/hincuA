package ru.job4j.sql.items;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Объявление.
 * @author Hincu Andrei (andreih1981@gmail.com)on 25.12.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class Advert {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yy, HH:mm");

    private int id;
    private String url;
    private String title;
    private String text;
    private Author author;
    private Calendar date = Calendar.getInstance();

    public Advert() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Advert advert = (Advert) o;
        if (id != advert.id) {
            return false;
        }
        if (url != null ? !url.equals(advert.url) : advert.url != null) {
            return false;
        }
        if (title != null ? !title.equals(advert.title) : advert.title != null) {
            return false;
        }
        if (text != null ? !text.equals(advert.text) : advert.text != null) {
            return false;
        }
        if (author != null ? !author.equals(advert.author) : advert.author != null) {
            return false;
        }
        return date != null ? date.equals(advert.date) : advert.date == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Advert :"
                + "ID :"
                + id
                + System.lineSeparator()
                + "Title = '"
                + title
                + "\',"
                + System.lineSeparator()
                + "url = '" + url
                + '\''
                + System.lineSeparator()
                + "text = '"
                + text
                + '\''
                + System.lineSeparator()
                + "Author = "
                + author
                + System.lineSeparator()
                + "Date = "
                + dateFormat.format(date.getTime())
                + System.lineSeparator()
                + "====================================================";
    }
}
