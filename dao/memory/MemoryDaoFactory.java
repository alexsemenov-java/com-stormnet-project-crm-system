package com.stormnet.crm.system.dao.memory;

import com.stormnet.crm.system.dao.PersonDao;
import com.stormnet.crm.system.dao.ActDao;
import com.stormnet.crm.system.dao.factory.DaoFactory;

public class MemoryDaoFactory extends DaoFactory {
    @Override
    public ActDao getActDao() {
        return new MemoryActDao();
    }

    @Override
    public PersonDao getClientDao() {
        return null;
    }

    @Override
    public PersonDao getManagerDao() {
        return null;
    }

    @Override
    public PersonDao getAdminDao() {
        return null;
    }
}
