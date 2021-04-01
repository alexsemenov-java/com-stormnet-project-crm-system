package com.stormnet.crm.system.serverservice.impl;

import com.stormnet.crm.system.dao.PersonDao;
import com.stormnet.crm.system.dao.factory.DaoFactory;
import com.stormnet.crm.system.obj.Admin;
import com.stormnet.crm.system.serverservice.PersonService;

import java.util.List;

public class AdminServiceImpl implements PersonService<Admin> {

    private PersonDao<Admin> dao;

    public AdminServiceImpl() {
        this.dao = DaoFactory.getFactory().getAdminDao();
    }

    @Override
    public void save(Admin person) {
        dao.save(person);
    }

    @Override
    public List<Admin> loadAll() {
        return dao.loadAll();
    }

    @Override
    public Admin loadById(Integer id) {
        return dao.loadById(id);
    }

    @Override
    public void delete(Integer id) {
        dao.delete(id);
    }

    @Override
    public void update(Admin person) {
        dao.update(person);
    }

    @Override
    public Admin loadPersonByLoginAndPassword(String login, String password) {
        return dao.loadPersonByLoginAndPassword(login, password);
    }
}
