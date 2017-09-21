package ru.job4j.models;

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
}
