package com.stormnet.crm.system.dao;

import com.stormnet.crm.system.obj.Id;

import java.util.List;

public interface BaseDao <T extends Id>{

        List<T> loadAll();

        T loadById(Integer id);

        void save(T object);

        void update(T object);

        void delete(Integer id);
}
