package com.miro.widget.repository;

import java.util.List;

public interface Repository<T> {

    T findById(Integer id);

    List<T> findAll();

    T persist(T entity);

    T remove(Integer id);
}
