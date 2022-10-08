package ua.goit.jdbc.repository;

import java.util.List;

public interface Repository<T> {
    T save(T entity);
    void delete(T entity);
    T findById(int id);
    List<T> findAll();
}
