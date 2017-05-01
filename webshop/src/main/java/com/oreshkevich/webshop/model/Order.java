package com.oreshkevich.webshop.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Order {
    private int id;
    private User user;
    private LocalDate dateOfOrdering;
    private LocalDate dateOfGetting;
    private String status;
    private BigDecimal amount;

    public Order(int id, User user, LocalDate dateOfOrdering, LocalDate dateOfGetting, String status,
                 BigDecimal amount) {

        this.id = id;
        this.user = user;
        this.dateOfOrdering = dateOfOrdering;
        this.dateOfGetting = dateOfGetting;
        this.status = status;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDateOfOrdering() {
        return dateOfOrdering;
    }

    public void setDateOfOrdering(LocalDate dateOfOrdering) {
        this.dateOfOrdering = dateOfOrdering;
    }

    public LocalDate getDateOfGetting() {
        return dateOfGetting;
    }

    public void setDateOfGetting(LocalDate dateOfGetting) {
        this.dateOfGetting = dateOfGetting;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (amount != order.amount) return false;
        if (!user.equals(order.user)) return false;
        if (!dateOfOrdering.equals(order.dateOfOrdering)) return false;
        if (!dateOfGetting.equals(order.dateOfGetting)) return false;
        return status.equals(order.status);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + user.hashCode();
        result = 31 * result + dateOfOrdering.hashCode();
        result = 31 * result + dateOfGetting.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + amount.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", dateOfOrdering=" + dateOfOrdering +
                ", dateOfGetting=" + dateOfGetting +
                ", status='" + status + '\'' +
                ", amount=" + amount +
                '}';
    }
}