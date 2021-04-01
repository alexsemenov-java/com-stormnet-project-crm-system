package com.stormnet.crm.system.dao.memory;

import com.stormnet.crm.system.dao.ActDao;
import com.stormnet.crm.system.db.IdGenerator;
import com.stormnet.crm.system.obj.Act;

import java.util.ArrayList;
import java.util.List;

public class MemoryActDao implements ActDao {
    private static List<Act> allActDb = initActDb();

    public MemoryActDao() {
    }

    @Override
    public List<Act> loadAll() {
        return allActDb;
    }

    @Override
    public Act loadById(Integer actID) {
        for (Act act : allActDb) {
            if (act.getId().equals(actID)) {
                return act;
            }
        }

        return null;
    }

    @Override
    public void save(Act act) {
        Integer actId = IdGenerator.getGenerator().nextId();
        act.setId(actId);

        allActDb.add(act);

    }

    @Override
    public void update(Act act) {
        Integer actID = act.getId();
        if (actID == null) {
            return;
        }

        Act dbAct = loadById(actID);
        dbAct.setTime(act.getTime());
        dbAct.setManagerLastName(act.getManagerLastName());

    }

    @Override
    public void delete(Integer actId) {
        for (Act act : allActDb) {
            if (act.getId().equals(actId)) {
                allActDb.remove(act);
                return;
            }
        }
    }

    private static List<Act> initActDb() {
        List<Act> actsDb = new ArrayList<>();

        Act t1 = new Act();
        t1.setId(3);
        t1.setClientId(1);
        t1.setManagerId(2);
        t1.setManagerLastName("Ivan");
        t1.setManagerLastName("Ivanov");
        t1.setClientFirstName("Semen");
        t1.setManagerLastName("Semenov");

        t1.setFinished(false);

        Act t2 = new Act();
        t2.setId(4);
        t2.setClientId(2);
        t2.setManagerId(2);
        t2.setManagerLastName("Ivan");
        t2.setManagerLastName("Ivanov");
        t2.setClientFirstName("Semen");
        t2.setManagerLastName("Semenov");

        t2.setFinished(true);

        actsDb.add(t1);
        actsDb.add(t2);

        return actsDb;
    }
}
