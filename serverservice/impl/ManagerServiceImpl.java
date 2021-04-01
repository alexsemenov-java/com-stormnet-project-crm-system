package com.stormnet.crm.system.serverservice.impl;

import com.stormnet.crm.system.dao.PersonDao;
import com.stormnet.crm.system.dao.factory.DaoFactory;
import com.stormnet.crm.system.obj.Manager;
import com.stormnet.crm.system.serverservice.PersonService;

import java.util.List;

public class ManagerServiceImpl implements PersonService<Manager> {

    private PersonDao<Manager> dao;

    public ManagerServiceImpl() {
        this.dao = DaoFactory.getFactory().getManagerDao();
    }

    @Override
    public void save(Manager person) {
        dao.save(person);
    }

    @Override
    public List<Manager> loadAll() {
        return dao.loadAll();
    }

    @Override
    public Manager loadById(Integer id) {
        return dao.loadById(id);
    }

    @Override
    public void delete(Integer id) {
        dao.delete(id);
    }

    @Override
    public void update(Manager person) {
        dao.update(person);
    }

    @Override
    public Manager loadPersonByLoginAndPassword(String login, String password) {
        return dao.loadPersonByLoginAndPassword(login, password);
    }
}
