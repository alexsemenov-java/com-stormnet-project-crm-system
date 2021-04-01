package com.stormnet.crm.system.clientservice.factory;

import com.stormnet.crm.system.clientservice.PersonService;
import com.stormnet.crm.system.clientservice.ActService;
import com.stormnet.crm.system.obj.Admin;
import com.stormnet.crm.system.obj.Client;
import com.stormnet.crm.system.obj.Manager;

public abstract class ServiceFactory {

    public abstract ActService getActService();

    public abstract PersonService<Client> getClientService();

    public abstract PersonService<Manager> getManagerService();

    public abstract PersonService<Admin> getAdminService();

    public static ServiceFactory getServiceFactory(){
        return new ServiceFactoryImpl();
    }

}
