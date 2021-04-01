package com.stormnet.crm.system.dao.memory;

import com.stormnet.crm.system.dao.PersonDao;
import com.stormnet.crm.system.obj.Id;
import com.stormnet.crm.system.obj.Person;

import java.util.List;

public class MemoryPersonDao implements PersonDao {
    @Override
    public Person loadPersonByLoginAndPassword(String login, String password) {
        return null;
    }

    @Override
    public List loadAll() {
        return null;
    }

    @Override
    public Id loadById(Integer id) {
        return null;
    }

    @Override
    public void save(Id object) {

    }

    @Override
    public void update(Id object) {

    }

    @Override
    public void delete(Integer id) {

    }
}
