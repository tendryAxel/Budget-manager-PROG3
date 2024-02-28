package com.example.walletprog3.repository;

import com.example.walletprog3.model.DefaultModel;

import java.util.List;

public interface CrudOperations<T extends DefaultModel, ID> {
    List<T> findAll();
    List<T> saveAll(List<T> toSave);
    T save(T toSave);
    T findById(ID id);
    T delete(ID id);
}
