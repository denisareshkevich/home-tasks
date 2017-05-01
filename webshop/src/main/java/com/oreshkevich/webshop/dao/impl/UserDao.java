package com.oreshkevich.webshop.dao.impl;

import com.oreshkevich.webshop.dao.ICRUDDao;
import com.oreshkevich.webshop.dao.connection.ConnectionManager;
import com.oreshkevich.webshop.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

/**
 * Created by Denis Areshkevich on 11.04.2017.
 */
public class UserDao implements ICRUDDao<User,Integer> {

    private static UserDao INSTANCE = null;

    public static UserDao getInstance() {
        if (INSTANCE == null) {
            synchronized (UserDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UserDao();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public Optional<User> add(User user) {
        try (Connection connection = ConnectionManager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO user" +
                    " (name, surname, email, password, address, telephone_number)" +
                    " VALUES (?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, user.getName());
                statement.setString(2, user.getSurname());
                statement.setString(3, user.getEmail());
                statement.setString(4, user.getPassword());
                statement.setString(5, user.getAddress());
                statement.setString(6, user.getTelephoneNumber());
                try(ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next())
                        user.setId(generatedKeys.getInt("id"));
                    return Optional.of(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getById(Integer id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM user" +
                    " WHERE id = ?")) {
                statement.setInt(1, id);
                try(ResultSet resultSet  = statement.executeQuery()) {
                    if (resultSet.next())
                    return Optional.of(new User(id, resultSet.getString("name"),
                            resultSet.getString("surname"),
                            resultSet.getString("email"),
                            resultSet.getString("password"),
                            resultSet.getString("address"),
                            resultSet.getString("telephone_number")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Optional<User>> getAll() {
        List<Optional<User>> users = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM user ")) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        users.add(Optional.of(new User(resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("surname"),
                                resultSet.getString("email"),
                                resultSet.getString("password"),
                                resultSet.getString("address"),
                                resultSet.getString("telephone_number"))));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public Optional<User> update(User user) {
        try (Connection connection = ConnectionManager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("UPDATE user" +
                    " SET name = ?, surname = ?, email = ?, password = ?, address = ?," +
                    "telephone_number = ?, WHERE id = ?")) {
                statement.setString(1, user.getName());
                statement.setString(2, user.getSurname());
                statement.setString(3, user.getEmail());
                statement.setString(4, user.getPassword());
                statement.setString(5, user.getAddress());
                statement.setString(6, user.getTelephoneNumber());
                statement.setInt(7, user.getId());
                statement.executeUpdate();
                    return Optional.of(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void delete(Integer id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM user" +
                    " WHERE id = ?")) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

