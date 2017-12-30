package ru.job4j.sql.items;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 30.12.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class Item {
    private String url;
    private Calendar publicationDate;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yy, HH:mm");

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        if (url != null ? !url.equals(item.url) : item.url != null) {
            return false;
        }
        return publicationDate != null ? publicationDate.equals(item.publicationDate) : item.publicationDate == null;
    }

    @Override
    public int hashCode() {
        int result = url != null ? url.hashCode() : 0;
        result = 31 * result + (publicationDate != null ? publicationDate.hashCode() : 0);
        return result;
    }

    public String getUrl() {

        return url;
    }

    @Override
    public String toString() {
        String date =  publicationDate == null ? "no date to show" : dateFormat.format(publicationDate.getTime());
        return "Item{"
                + "url='"
                + url
                + System.lineSeparator()
                + "publicationDate="
                + date
                + '}'
                + System.lineSeparator()
                + "================================================";
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Calendar getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Calendar publicationDate) {
        this.publicationDate = publicationDate;
    }
}
