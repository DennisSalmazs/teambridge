package com.teambridge.service;

import com.teambridge.dto.RoleDTO;

import java.util.List;

public interface CrudService<T,ID> {

    T save(T object);
    T findById(ID id);
    List<T> findAll();
    void update(T object);
    void deleteById(ID id);

}
