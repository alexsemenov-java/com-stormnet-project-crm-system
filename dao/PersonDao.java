package com.stormnet.crm.system.dao;

import com.stormnet.crm.system.dao.BaseDao;
import com.stormnet.crm.system.obj.Person;

public interface PersonDao <T extends Person> extends BaseDao<T> {

    T loadPersonByLoginAndPassword(String login, String password);
}
