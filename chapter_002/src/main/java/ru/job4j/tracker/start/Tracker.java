package ru.job4j.tracker.start;

import ru.job4j.tracker.connection.ConnectionSQL;
import ru.job4j.tracker.connection.Query;
import ru.job4j.tracker.models.Comment;
import ru.job4j.tracker.models.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 04.09.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class Tracker {
    /**
     * Хранилище заявок.
     */
    //   private Item[] items = new Item[100];
    private ArrayList<Item> items;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;

    public Tracker() {
        this.connection = new ConnectionSQL().getConnection();
        init();
    }

    private void init() {
        try {
            statement = connection.createStatement();
            statement.executeUpdate(Query.CREATE_TRACKER_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Добавление заявок.
     * @param item - заявка.
     * @return - заявка.
     */
    public Item add(Item item) {
        try {
            preparedStatement = connection.prepareStatement(Query.INSERT_NEW_ITEM);
            preparedStatement.setString(1, item.getName());
            preparedStatement.setString(2, item.getDesc());
            preparedStatement.setTimestamp(3, new Timestamp(item.getCreated()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }
    /**
     * Редактирование  заявок.
     * @param item item.
     */
    public void update(Item item) {
        if (item != null) {
            int index = 0;
            for (Item item1 : this.items) {
                if (item1.getId().equals(item.getId())) {
                    index = items.indexOf(item1);
                    break;
                }
            }
            this.items.set(index, item);
        }
    }

    /**
     * Удаление заявок.
     * @param item item.
     */
    public void delete(Item item) {
        if (item != null) {
            if (this.items.contains(item)) {
                this.items.remove(item);
            }
        }
    }
    /**
     * Получение списка всех заявок.
     * @return массив заявок.
     */
    public ArrayList<Item> getAll() {
        if (items == null) {
            items = new ArrayList<>();
        } else {
            items.clear();
        }
        try {
            ResultSet rs = statement.executeQuery(Query.SELECT_ALL_ITEMS);
            while (rs.next()) {
                Item item = new Item();
                item.setId(rs.getString("id"));
                item.setName(rs.getString("name"));
                item.setDesc(rs.getString("description"));
                item.setCreated(rs.getTimestamp("create_date").getTime());
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }
    /**
     * Получение списка по имени.
     * @param key key.
     * @return item.
     */
    public ArrayList<Item> findByName(String key) {
        ArrayList<Item> list = new ArrayList();
        for (Item item : items) {
            if (item.getName().equals(key)) {
                list.add(item);
            }
        }
        return list;
    }

    /**
     * Получение заявки по id.
     * @param id id.
     * @return item.
     */
    public Item findById(String id) {
        Item item = null;
        try {
            preparedStatement = connection.prepareStatement(Query.SELECT_BY_ID);
            preparedStatement.setInt(1, Integer.parseInt(id));
            ResultSet rs = preparedStatement.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    item = new Item();
                    item.setId(rs.getString("id"));
                    item.setName(rs.getString("name"));
                    item.setDesc(rs.getString("description"));
                    item.setCreated(rs.getTimestamp("create_date").getTime());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Comment> getAllComments(String id) {
        List<Comment> comments = new ArrayList<>();
        //todo
        try {
            preparedStatement = connection.prepareStatement(Query.SELECT_ALL_COMMENTS);
            preparedStatement.setInt(1, Integer.parseInt(id));
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setId(rs.getInt("id"));
                comment.setText(rs.getString("description"));
                comment.setCreate(rs.getTimestamp("data_create").getTime());
                comments.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }
    public void addNewComment(String id, String text) {
        try {
            statement.executeUpdate(Query.CREATE_COMMENTS_TABLE);
            preparedStatement = connection.prepareStatement(Query.INSERT_NEW_COMMENT);
            preparedStatement.setInt(1, Integer.parseInt(id));
            preparedStatement.setString(2, text);
            preparedStatement.setTimestamp(3, new Timestamp(new Date(System.currentTimeMillis()).getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
