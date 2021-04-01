package com.stormnet.crm.system.dao.xml;

import com.stormnet.crm.system.dao.PersonDao;
import com.stormnet.crm.system.dao.ActDao;
import com.stormnet.crm.system.dao.factory.DaoFactory;
import com.stormnet.crm.system.obj.Admin;
import com.stormnet.crm.system.obj.Client;
import com.stormnet.crm.system.obj.Manager;

public class XmlDaoFactory extends DaoFactory {

    @Override
    public ActDao getActDao() {
        return new XmlActDao();
    }

    @Override
    public PersonDao<Client> getClientDao() {
        return new XmlClientDao();
    }

    @Override
    public PersonDao<Manager> getManagerDao() {
        return new XmlManagerDao();
    }

    @Override
    public PersonDao<Admin> getAdminDao() {
        return new XmlAdminDao();
    }
}
