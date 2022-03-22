package ua.com.javenier.service;

import ua.com.javenier.entity.BaseEntity;

import java.util.List;

public interface BaseService<E extends BaseEntity> {

    E update(E entity);
    void delete(Long id);
    E findById(Long id);
    List<E> findAll();
}
