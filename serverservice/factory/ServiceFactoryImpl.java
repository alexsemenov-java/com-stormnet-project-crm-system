package com.stormnet.crm.system.serverservice.factory;

import com.stormnet.crm.system.obj.Admin;
import com.stormnet.crm.system.obj.Client;
import com.stormnet.crm.system.obj.Manager;
import com.stormnet.crm.system.serverservice.PersonService;
import com.stormnet.crm.system.serverservice.ActService;
import com.stormnet.crm.system.serverservice.impl.AdminServiceImpl;
import com.stormnet.crm.system.serverservice.impl.ClientServiceImpl;
import com.stormnet.crm.system.serverservice.impl.ManagerServiceImpl;
import com.stormnet.crm.system.serverservice.impl.ActServiceImpl;

public class ServiceFactoryImpl extends ServiceFactory{

    @Override
    public ActService getActService() {
        return new ActServiceImpl();
    }

    @Override
    public PersonService<Client> getClientService() {
        return new ClientServiceImpl();
    }

    @Override
    public PersonService<Manager> getManagerService() {
        return new ManagerServiceImpl();
    }

    @Override
    public PersonService<Admin> getAdminService() {
        return new AdminServiceImpl();
    }
}
