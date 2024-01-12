package repository;

import model.DefaultModel;

import java.sql.SQLException;
import java.util.List;

public interface CrudOperations<T extends DefaultModel, ID> {
    List<T> findAll() throws SQLException;
    List<T> saveAll(List<T> toSave) throws SQLException;
    T save(T toSave) throws SQLException;
    T findById(ID id) throws SQLException;
    T delete(ID id) throws SQLException;
}
