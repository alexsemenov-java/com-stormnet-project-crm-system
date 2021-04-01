package com.stormnet.crm.system.clientservice;

import com.stormnet.crm.system.obj.Person;

import java.io.IOException;
import java.util.List;

public interface PersonService <T extends Person>{
    void save(T person) throws IOException;

    List<T> loadAll() throws IOException;

    T loadById(Integer id) throws IOException;

    void delete(Integer id) throws IOException;

    void update(T person) throws IOException;

    T loadPersonByLoginAndPassword(String login, String password) throws IOException;
}
