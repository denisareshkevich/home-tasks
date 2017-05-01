package com.oreshkevich.webshop.dao;

import java.util.List;
import java.util.Optional;

/**
 * Created by Denis Areshkevich on 09.04.2017.
 */
public interface ICRUDDao<T, ID> {

    Optional<T> add(T object);

    Optional<T> getById(ID id);

    List<Optional<T>> getAll();

    Optional<T> update(T object);

    void delete(ID id);

}
