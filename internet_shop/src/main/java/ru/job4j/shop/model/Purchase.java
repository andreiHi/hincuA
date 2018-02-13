package ru.job4j.shop.model;

import java.sql.Timestamp;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 13.02.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class Purchase {
//•	ID
//•	ID пользователя
//•	ID товара
//•	Дата покупки
//•	Цена покупки
//•	Количество единиц товара

    private int id;
    private int userId;
    private int productId;
    private Timestamp data;
    private int price;
    private int quantity;

    public Purchase(int id, int userId, int productId, Timestamp data, int price, int quantity) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.data = data;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Purchase{"
                + "id="
                + id
                + ", userId="
                + userId
                + ", productId="
                + productId
                + ", data="
                + data
                + ", price="
                + price
                + ", quantity="
                + quantity
                + '}';
    }
}
