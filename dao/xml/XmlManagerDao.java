package com.stormnet.crm.system.dao.xml;

import com.stormnet.crm.system.dao.PersonDao;
import com.stormnet.crm.system.dao.exception.InvalidIdException;
import com.stormnet.crm.system.dao.exception.ObjectAlreadyStoredException;
import com.stormnet.crm.system.dao.exception.ObjectNotFoundException;
import com.stormnet.crm.system.db.xml.XmlDb;
import com.stormnet.crm.system.db.xml.XmlDbTable;
import com.stormnet.crm.system.obj.Manager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;



public class XmlManagerDao implements PersonDao<Manager> {
    @Override
    public Manager loadPersonByLoginAndPassword(String login, String password) {
        List<Manager> all = loadAll();

        for (Manager manager : all) {
            if (manager.getLogin().equals(login) && manager.getPassword().equals(password)) {
                return manager;
            }
        }

        return null;
    }

    @Override
    public List<Manager> loadAll() {
        XmlDb xmlDb = XmlDb.getDb();
        Document document = xmlDb.getXmlDocumentForTable(XmlDbTable.Managers);

        List<Manager> all = new ArrayList<>();
        NodeList tagList = document.getElementsByTagName("person");
        for (int i = 0; i < tagList.getLength(); i++) {
            Element tag = (Element) tagList.item(i);

            String idStr = tag.getAttribute("id");
            Integer id = Integer.valueOf(idStr);

            String firstNameStr = tag.getAttribute("firstName");

            String passwordStr = tag.getAttribute("password");

            String loginStr = tag.getAttribute("login");

            String lastNameStr = tag.getAttribute("lastName");

            String officeStr = tag.getAttribute("office");

            Manager person = new Manager();
            person.setId(id);
            person.setFirstName(firstNameStr);
            person.setLastName(lastNameStr);
            person.setPassword(passwordStr);
            person.setLogin(loginStr);
            person.setOffice(officeStr);

            all.add(person);
        }

        return all;
    }

    @Override
    public Manager loadById(Integer id) {
        if (id == null) {
            throw new InvalidIdException();
        }

        List<Manager> all = loadAll();
        for (Manager manager : all) {
            if (id.equals(manager.getId())) {
                return manager;
            }
        }

        throw new ObjectNotFoundException();
    }

    @Override
    public void save(Manager object) {
        Integer id = object.getId();
        if (id != null) {
            throw new ObjectAlreadyStoredException();
        }

        XmlDb xmlDb = XmlDb.getDb();

        id = XmlDb.getDb().getNextIdForTable();
        object.setId(id);

        Document document = xmlDb.getXmlDocumentForTable(XmlDbTable.Managers);

        Element tag = document.createElement("person");

        tag.setAttribute("id", Integer.toString(id));
        tag.setAttribute("firstName", object.getFirstName());
        tag.setAttribute("lastName", object.getLastName());
        tag.setAttribute("password", object.getPassword());
        tag.setAttribute("login", object.getLogin());
        tag.setAttribute("office", object.getOffice());

        document.getDocumentElement().appendChild(tag);

        xmlDb.saveDocumentForTable(XmlDbTable.Managers);

    }

    @Override
    public void update(Manager object) {

        Integer id = object.getId();
        if (id == null) {
            return;
        }
        Manager stored = loadById(id);
        if (stored == null) {
            return;
        }

        XmlDb xmlDb = XmlDb.getDb();
        Document document = xmlDb.getXmlDocumentForTable(XmlDbTable.Managers);

        Element tag = document.createElement("person");

        tag.setAttribute("id", Integer.toString(id));
        tag.setAttribute("firstName", object.getFirstName());
        tag.setAttribute("lastName", object.getLastName());
        tag.setAttribute("password", object.getPassword());
        tag.setAttribute("login", object.getLogin());
        tag.setAttribute("office", object.getOffice());

        document.getDocumentElement().appendChild(tag);

        xmlDb.updateDocumentForTable(id, "person", tag, XmlDbTable.Managers);

    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            return;
        }
        XmlDb xmlDb = XmlDb.getDb();
        xmlDb.deleteFromDocumentForTable(id, "person", XmlDbTable.Managers);

    }
}


