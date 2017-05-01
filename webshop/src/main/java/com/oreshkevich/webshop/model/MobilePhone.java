package com.oreshkevich.webshop.model;

import java.math.BigDecimal;

public class MobilePhone {

    private int id;
    private Brand brand;
    private String model;
    private Color color;
    private BigDecimal price;
    private int amount;

    public MobilePhone(int id, Brand brand, String model, Color color, BigDecimal price, int amount) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.price = price;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MobilePhone)) return false;

        MobilePhone that = (MobilePhone) o;

        if (id != that.id) return false;
        if (amount != that.amount) return false;
        if (!brand.equals(that.brand)) return false;
        if (!model.equals(that.model)) return false;
        if (!color.equals(that.color)) return false;
        return price.equals(that.price);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + brand.hashCode();
        result = 31 * result + model.hashCode();
        result = 31 * result + color.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + amount;
        return result;
    }

    @Override
    public String toString() {
        return "MobilePhone{" +
                "id=" + id +
                ", brand=" + brand +
                ", model='" + model + '\'' +
                ", color=" + color +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }
}
