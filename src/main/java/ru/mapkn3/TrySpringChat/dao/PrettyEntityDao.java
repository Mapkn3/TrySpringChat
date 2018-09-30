package ru.mapkn3.TrySpringChat.dao;

import ru.mapkn3.TrySpringChat.model.PrettyEntity;

import java.io.Serializable;
import java.util.List;

public interface PrettyEntityDao {
    Serializable save(PrettyEntity entity);

    <E extends PrettyEntity> E findById(Class<E> entityClass, Serializable id);

    List getAll(Class entityClass);

    <E extends PrettyEntity> E update(E entity);

    void delete(PrettyEntity entity);
}
