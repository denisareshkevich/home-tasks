package com.oreshkevich.webshop.dao.impl;

import com.oreshkevich.webshop.dao.ICRUDDao;
import com.oreshkevich.webshop.dao.connection.ConnectionManager;
import com.oreshkevich.webshop.model.Order;
import com.oreshkevich.webshop.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Denis Areshkevich on 26.04.2017.
 */
public class OrderDao implements ICRUDDao<Order, Integer> {

    private static OrderDao INSTANCE = null;

    public static OrderDao getInstance() {
        if (INSTANCE == null) {
            synchronized (OrderDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new OrderDao();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public Optional<Order> add(Order order) {
        try (Connection connection = ConnectionManager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO order" +
                    " ( user_id, date_of_ordering, date_of_getting, status, amount)" +
                    " VALUES (?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1, order.getUser().getId());
                statement.setDate(2, Date.valueOf(order.getDateOfOrdering()));
                statement.setDate(3, Date.valueOf(order.getDateOfGetting()));
                statement.setString(4, order.getStatus());
                statement.setBigDecimal(5, order.getAmount());
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        order.setId(generatedKeys.getInt("id"));
                        return Optional.of(order);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Order> getById(Integer id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM order" +
                    " WHERE id = ?")) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Optional<User> userOptional = UserDao.getInstance()
                                .getById(resultSet.getInt("user_id"));
                        if (userOptional.isPresent()) {
                            return Optional.of(new Order(id, userOptional.get(),
                                    resultSet.getDate("date_of_ordering").toLocalDate(),
                                    resultSet.getDate("date_of_getting").toLocalDate(),
                                    resultSet.getString("status"),
                                    resultSet.getBigDecimal("amount")));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Optional<Order>> getAll() {
        List<Optional<Order>> orderList = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM order")) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Optional<User> userOptional = UserDao.getInstance()
                                .getById(resultSet.getInt("user_id"));
                        if (userOptional.isPresent()) {
                            orderList.add(Optional.of(new Order(resultSet.getInt("id"), userOptional.get(),
                                    resultSet.getDate("date_of_ordering").toLocalDate(),
                                    resultSet.getDate("date_of_getting").toLocalDate(),
                                    resultSet.getString("status"),
                                    resultSet.getBigDecimal("amount"))));
                            return orderList;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    @Override
    public Optional<Order> update(Order order) {
        try (Connection connection = ConnectionManager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("UPDATE order" +
                    "  user_id = ?, date_of_ordering = ?, date_of_getting = ?, status = ?," +
                    " amount = ? WHERE id = ?")) {
                statement.setInt(1, order.getUser().getId());
                statement.setDate(2, Date.valueOf(order.getDateOfOrdering()));
                statement.setDate(3, Date.valueOf(order.getDateOfGetting()));
                statement.setString(4, order.getStatus());
                statement.setBigDecimal(5, order.getAmount());
                statement.setInt(6, order.getId());
                return Optional.of(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void delete(Integer id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM order" +
                    " WHERE id = ?")) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
