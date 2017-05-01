package com.oreshkevich.webshop.dao.impl;

import com.oreshkevich.webshop.dao.ICRUDDao;
import com.oreshkevich.webshop.dao.connection.ConnectionManager;
import com.oreshkevich.webshop.model.Brand;
import com.oreshkevich.webshop.model.Color;
import com.oreshkevich.webshop.model.MobilePhone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

/**
 * Created by Denis Areshkevich on 06.04.2017.
 */
public class MobilePhoneDao implements ICRUDDao<MobilePhone,Integer> {

    private static MobilePhoneDao INSTANCE = null;

    public static MobilePhoneDao getInstance() {
        if (INSTANCE == null) {
            synchronized (MobilePhoneDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MobilePhoneDao();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public Optional<MobilePhone> add(MobilePhone phone) {
        try (Connection connection = ConnectionManager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO mobile_phone" +
                    " (brand_id, model, color_id, price, amount)" +
                    " VALUES (?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1, phone.getBrand().getId());
                statement.setString(2, phone.getModel());
                statement.setInt(3, phone.getColor().getId());
                statement.setBigDecimal(4, phone.getPrice());
                statement.setInt(5, phone.getAmount());
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        phone.setId(generatedKeys.getInt("id"));
                        return Optional.of(phone);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<MobilePhone> getById(Integer id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM" +
                    " mobile_phone WHERE id = ?")) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Optional<Brand> brandOptional
                                = BrandDao.getInstance().getById(resultSet.getInt("brand_id"));
                        Optional<Color> colorOptional
                                = ColorDao.getInstance().getById(resultSet.getInt("color_id"));
                        if (brandOptional.isPresent() & colorOptional.isPresent()) {
                            return Optional.of(new MobilePhone(id,
                                    brandOptional.get(),
                                    resultSet.getString("model"),
                                    colorOptional.get(),
                                    resultSet.getBigDecimal("price"),
                                    resultSet.getInt("amount")));
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
    public List<Optional<MobilePhone>> getAll() {
        List<Optional<MobilePhone>> phones = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM mobile_phone")) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Optional<Brand> brandOptional
                                = BrandDao.getInstance().getById(resultSet.getInt("brand_id"));
                        Optional<Color> colorOptional
                                = ColorDao.getInstance().getById(resultSet.getInt("color_id"));
                        if (brandOptional.isPresent() & colorOptional.isPresent()) {
                            phones.add(Optional.of(new MobilePhone(resultSet.getInt("id"),
                                    brandOptional.get(),
                                    resultSet.getString("model"),
                                    colorOptional.get(),
                                    resultSet.getBigDecimal("price"),
                                    resultSet.getInt("amount"))));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phones;
    }

    @Override
    public Optional<MobilePhone> update(MobilePhone phone) {
        try (Connection connection = ConnectionManager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("UPDATE mobile_phone" +
                    " SET brand_id = ?, model = ?, color_id = ?, price = ?, amount = ?  WHERE id = ?")) {
                statement.setInt(1, phone.getBrand().getId());
                statement.setString(2, phone.getModel());
                statement.setInt(3, phone.getBrand().getId());
                statement.setBigDecimal(4, phone.getPrice());
                statement.setInt(5, phone.getAmount());
                statement.setInt(6, phone.getId());
                statement.executeUpdate();
                        return Optional.of(phone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void delete(Integer id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM mobile_phone" +
                    " WHERE id = ?")) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<MobilePhone> addBrand(MobilePhone phone, Brand brand) {
        try (Connection connection = ConnectionManager.getConnection()) {
            connection.setAutoCommit(false);
            BrandDao.getInstance().add(brand);
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO mobile_phone" +
                    " (brand_id) VALUES (?) WHERE id = ?")) {
                statement.setInt(1, brand.getId());
                statement.setInt(2, phone.getId());
               statement.executeUpdate();
            }
            connection.commit();
            phone.setBrand(brand);
            return Optional.of(phone);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  Optional.empty();
    }

    public Optional<MobilePhone> addColor(MobilePhone phone, Color color) {
        try (Connection connection = ConnectionManager.getConnection()) {
            connection.setAutoCommit(false);
            ColorDao.getInstance().add(color);
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO mobile_phone" +
                    " (color_id) VALUES (?) WHERE id = ?")) {
                statement.setInt(1, color.getId());
                statement.setInt(2, phone.getId());
                statement.executeUpdate();
            }
            connection.commit();
            phone.setColor(color);
            return Optional.of(phone);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  Optional.empty();
    }
}