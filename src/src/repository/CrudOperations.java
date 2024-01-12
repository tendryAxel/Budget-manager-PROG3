package repository;

import model.DefaultModel;

import java.sql.SQLException;
import java.util.List;

public interface CrudOperations<T extends DefaultModel> {
    List<T> findAll() throws SQLException;
    List<T> saveAll(List<T> toSave) throws SQLException;
    T save(T toSave) throws SQLException;
    T findById(int id) throws SQLException;
    T delete(int id) throws SQLException;
}
