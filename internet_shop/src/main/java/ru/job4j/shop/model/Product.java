package ru.job4j.shop.model;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 13.02.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class Product {
//•	ID
//•	Название
//•	Описание
//•	Цена
//•	Количество на складе


    private int id;
    private String name;
    private String description;
    private int price;
    private int amount;

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Product{"
                + "id="
                + id
                + ", name='"
                + name
                + '\''
                + ", description='"
                + description
                + '\''
                + ", price="
                + price
                + ", amount="
                + amount
                + '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Product(int id, String name, String description, int price, int amount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
    }
}
