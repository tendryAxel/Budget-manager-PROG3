package repository;

import model.DefaultModel;

import java.sql.SQLException;
import java.util.List;

public interface CrudOperations<T extends DefaultModel, ID> {
    List<T> findAll();
    List<T> saveAll(List<T> toSave);
    T save(T toSave);
    T findById(ID id);
    T delete(ID id);
}
