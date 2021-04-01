package com.stormnet.crm.system.clientservice;

import com.stormnet.crm.system.obj.Act;

import java.io.IOException;
import java.util.List;

public interface ActService {
    void save(Act act) throws IOException;

    List<Act> loadAll() throws IOException;

    Act loadById(Integer id) throws IOException;

    void delete(Integer id) throws IOException;

    void update(Act act) throws IOException;
}
