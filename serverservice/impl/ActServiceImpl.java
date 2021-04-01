package com.stormnet.crm.system.serverservice.impl;

import com.stormnet.crm.system.dao.ActDao;
import com.stormnet.crm.system.dao.factory.DaoFactory;
import com.stormnet.crm.system.obj.Act;
import com.stormnet.crm.system.serverservice.ActService;

import java.util.List;

public class ActServiceImpl implements ActService {

    private ActDao dao;

    public ActServiceImpl() {
        this.dao = DaoFactory.getFactory().getActDao();
    }

    @Override
    public void save(Act act) {
        dao.save(act);
    }

    @Override
    public List<Act> loadAll() {
        return dao.loadAll();
    }

    @Override
    public Act loadById(Integer id) {
        return dao.loadById(id);
    }

    @Override
    public void delete(Integer id) {
        dao.delete(id);
    }

    @Override
    public void update(Act act) {
        dao.update(act);
    }
}
