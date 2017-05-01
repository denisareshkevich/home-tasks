package com.oreshkevich.webshop.dao.impl;


import com.oreshkevich.webshop.dao.connection.ConnectionManager;
import com.oreshkevich.webshop.model.Color;
import com.oreshkevich.webshop.dao.ICRUDDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

/**
 * Created by Denis Areshkevich on 12.04.2017.
 */
public class ColorDao implements ICRUDDao<Color,Integer> {

    private static ColorDao INSTANCE = null;

    public static ColorDao getInstance() {
        if (INSTANCE == null) {
            synchronized (ColorDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ColorDao();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public Optional<Color> add(Color color) {
        try (Connection connection = ConnectionManager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO color" +
                    " (name) VALUES (?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, color.getName());
                try(ResultSet generatedKeys =  statement.getGeneratedKeys()){
                    if(generatedKeys.next()){
                        color.setId(generatedKeys.getInt("id"));
                        return Optional.of(color);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Color> getById(Integer id) {
        try(Connection connection = ConnectionManager.getConnection()){
            try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM color" +
                    " WHERE id = ?")){
                statement.setInt(1, id);
                try(ResultSet resultSet =  statement.executeQuery()){
                    if(resultSet.next()){
                        return Optional.of(new Color(id, resultSet.getString("name")));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Optional<Color>> getAll() {
        List<Optional<Color>> colors = new ArrayList<>();
        try(Connection connection = ConnectionManager.getConnection()){
            try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM color")){
                try(ResultSet resultSet =  statement.executeQuery()){
                    while (resultSet.next()){
                        colors.add(Optional.of(new Color(resultSet.getInt("id"),
                                resultSet.getString("name"))));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return colors;
    }

    @Override
    public Optional<Color> update(Color color) {
        try (Connection connection = ConnectionManager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("UPDATE color SET" +
                    " NAME = ? WHERE id = ?")) {
                statement.setString(1, color.getName());
                statement.setInt(2, color.getId());
                statement.executeUpdate();
                        return Optional.of(color);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void delete(Integer id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM color" +
                    " WHERE id = ?")) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
