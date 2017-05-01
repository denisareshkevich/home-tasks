package com.oreshkevich.webshop.dao.impl;

import com.oreshkevich.webshop.dao.ICRUDDao;
import com.oreshkevich.webshop.dao.connection.ConnectionManager;
import com.oreshkevich.webshop.model.Brand;

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
public class BrandDao implements ICRUDDao<Brand, Integer> {

    private static BrandDao INSTANCE = null;

    public static BrandDao getInstance() {
        if (INSTANCE == null) {
            synchronized (BrandDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new BrandDao();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public Optional<Brand> add(Brand brand) {
        try (Connection connection = ConnectionManager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO brand" +
                    " (name) VALUES (?)",PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, brand.getName());
                try(ResultSet generatedKeys =  statement.getGeneratedKeys()){
                    if(generatedKeys.next()){
                        brand.setId(generatedKeys.getInt("id"));
                        return Optional.of(brand);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Brand> getById(Integer id) {
        try(Connection connection = ConnectionManager.getConnection()){
            try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM brand" +
                    " WHERE id = ?")){
                statement.setInt(1, id);
              try(ResultSet resultSet =  statement.executeQuery()){
                  if(resultSet.next()){
                      return Optional.of(new Brand(id, resultSet.getString("name")));
                  }
              }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Optional<Brand>> getAll() {
        List<Optional<Brand>> brands = new ArrayList<>();
        try(Connection connection = ConnectionManager.getConnection()){
            try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM brand")){
                try(ResultSet resultSet =  statement.executeQuery()){
                    while (resultSet.next()){
                        brands.add(Optional.of(new Brand(resultSet.getInt("id"),
                            resultSet.getString("name"))));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return brands;
    }

    @Override
    public Optional<Brand> update(Brand brand) {
        try (Connection connection = ConnectionManager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("UPDATE brand SET" +
                    " NAME = ? WHERE id = ?")) {
                statement.setString(1, brand.getName());
                statement.setInt(2, brand.getId());
                statement.executeUpdate();
                        return Optional.of(brand);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void delete(Integer id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM brand" +
                    " WHERE id = ?")) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
