package com.stormnet.crm.system.serverservice;

import com.stormnet.crm.system.obj.Person;

import java.util.List;

public interface PersonService <T extends Person>{

    void save(T person);

    List<T> loadAll();

    T loadById(Integer id);

    void delete(Integer id);

    void update(T person);

    T loadPersonByLoginAndPassword(String login, String password);
}
