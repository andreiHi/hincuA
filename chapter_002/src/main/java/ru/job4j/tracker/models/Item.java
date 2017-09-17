package ru.job4j.tracker.models;

import java.util.Arrays;

/**
 * Item - элемент трекера.
 * @author Hincu Andrei (andreih1981@gmail.com)on 04.09.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class Item {
    /**
     * id.
     */
    private String id;
    /**
     * name.
     */
    private String name;
    /**
     * desc.
     */
    private String desc;
    /**
     * created.
     */
    private long created;
    /**
     * comment.
     */
    private String[] comments;

    /**
     * Конструктор.
     */
    public Item() {

    }
    /**
     * Конструктор.
     * @param id id.
     * @param name name.
     * @param desc desk.
     * @param created created.
     * @param comments comment.
     */
    public Item(String id, String name, String desc, long created, String[] comments) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.created = created;
        this.comments = comments;
    }

    /**
     * Конструктор.
     * @param name name.
     * @param desc desc.
     * @param created created.
     */
    public Item(String name, String desc, long created) {
        this.name = name;
        this.desc = desc;
        this.created = created;
    }

    /**
     * Constructor.
     * @param name name.
     * @param desc description.
     */
    public Item(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    /**
     * Конструктор.
     * @param id id.
     * @param name name.
     * @param desc desc.
     */
    public Item(String id, String name, String desc) {
        this.id = id;
        this.name = name;
        this.desc = desc;
    }

    /**
     * геттер id.
     * @return id.
     */
    public String getId() {
        return id;
    }

    /**
     * сеттер id.
     * @param id id.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * геттер name.
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * сеттер name.
     * @param name name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * геттер desk.
     * @return desk.
     */
    public String getDesc() {
        return desc;
    }

    /**
     * сеттер деск.
     * @param desc desk
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * геттер created.
     * @return created.
     */
    public long getCreated() {
        return created;
    }

    /**
     * сеттер created.
     * @param created created.
     */
    public void setCreated(long created) {
        this.created = created;
    }

    /**
     * геттер comments.
     * @return comments.
     */
    public String[] getComments() {
        return comments;
    }

    /**
     * сеттер comennts.
     * @param comments comments.
     */
    public void setComments(String[] comments) {
        this.comments = comments;
    }

    /**
     * Распечатка итема.
     * @return String.
     */
    @Override
    public String toString() {
        return "Item{"
                + "id='"
                + id
                + '\''
                + ", name='"
                + name
                + '\''
                + ", desc='"
                + desc
                + '\''
                + ", created="
                + created
                + ", comments="
                + Arrays.toString(comments)
                + '}'
                + System.getProperty("line.separator");
    }

    /**
     * Equals.
     * @param o item.
     * @return true or false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Item item = (Item) o;

        if (created != item.created) {
            return false;
        }
        if (id != null ? !id.equals(item.id) : item.id != null) {
            return false;
        }
        if (name != null ? !name.equals(item.name) : item.name != null) {
            return false;
        }
        return desc != null ? desc.equals(item.desc) : item.desc == null;
    }

    /**
     * HashCode.
     * @return code.
     */
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (desc != null ? desc.hashCode() : 0);
        result = 31 * result + (int) (created ^ (created >>> 32));
        return result;
    }
}
