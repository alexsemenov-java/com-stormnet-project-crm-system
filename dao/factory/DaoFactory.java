package com.stormnet.crm.system.dao.factory;

import com.stormnet.crm.system.dao.PersonDao;
import com.stormnet.crm.system.dao.ActDao;
import com.stormnet.crm.system.dao.exception.UnknownDaoTypeException;
import com.stormnet.crm.system.dao.memory.MemoryDaoFactory;
import com.stormnet.crm.system.dao.xml.XmlDaoFactory;
import com.stormnet.crm.system.obj.Admin;
import com.stormnet.crm.system.obj.Client;
import com.stormnet.crm.system.obj.Manager;

public abstract class DaoFactory {

    public abstract ActDao getActDao();

    public abstract PersonDao<Client> getClientDao();

    public abstract PersonDao<Manager> getManagerDao();

    public abstract PersonDao<Admin> getAdminDao();

    public static DaoFactory getFactory() {
        return getFactory(DaoTypes.XmlDao);
    }

    public static DaoFactory getFactory(DaoTypes type) {
        switch (type) {
            case MemoryDao: {
                return new MemoryDaoFactory();
            }

            case XmlDao: {
                return new XmlDaoFactory();
            }
        }

        throw new UnknownDaoTypeException(type);
    }
}
