package com.stormnet.crm.system.serverservice;

import com.stormnet.crm.system.obj.Act;

import java.util.List;

public interface ActService {

    void save (Act act);

    List<Act> loadAll();

    Act loadById (Integer id);

    void delete (Integer id);

    void update (Act act);
}
