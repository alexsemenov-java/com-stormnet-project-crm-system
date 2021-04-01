package com.stormnet.crm.system.clientservice.factory;

import com.stormnet.crm.system.clientservice.PersonService;
import com.stormnet.crm.system.clientservice.ActService;
import com.stormnet.crm.system.clientservice.impl.AdminServiceImpl;
import com.stormnet.crm.system.clientservice.impl.ClientServiceImpl;
import com.stormnet.crm.system.clientservice.impl.ManagerServiceImpl;
import com.stormnet.crm.system.clientservice.impl.ActServiceImpl;
import com.stormnet.crm.system.obj.Admin;
import com.stormnet.crm.system.obj.Client;
import com.stormnet.crm.system.obj.Manager;

public class ServiceFactoryImpl extends ServiceFactory {
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
