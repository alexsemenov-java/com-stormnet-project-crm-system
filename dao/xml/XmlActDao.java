package com.stormnet.crm.system.dao.xml;

import com.stormnet.crm.system.dao.ActDao;
import com.stormnet.crm.system.dao.exception.InvalidIdException;
import com.stormnet.crm.system.dao.exception.ObjectAlreadyStoredException;
import com.stormnet.crm.system.dao.exception.ObjectNotFoundException;
import com.stormnet.crm.system.db.xml.XmlDb;
import com.stormnet.crm.system.db.xml.XmlDbTable;
import com.stormnet.crm.system.obj.Act;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class XmlActDao implements ActDao {

    @Override
    public List<Act> loadAll() {

        XmlDb xmlDb = XmlDb.getDb();
        Document actDocument = xmlDb.getXmlDocumentForTable(XmlDbTable.Acts);

        List<Act> allActs = new ArrayList<>();
        NodeList actTagList = actDocument.getElementsByTagName("act");
        for (int i = 0; i < actTagList.getLength(); i++) {
            Element actTag = (Element) actTagList.item(i);

            String idStr = actTag.getAttribute("id");
            Integer actId = Integer.valueOf(idStr);

            String clientIdStr = actTag.getAttribute("clientId");
            Integer clientId = Integer.valueOf(clientIdStr);

            String isFinishedStr = actTag.getAttribute("isFinished");
            Boolean isFinished = Boolean.valueOf(isFinishedStr);

            String managerLastNameStr = actTag.getAttribute("managerLastName");

            String managerFirstNameStr = actTag.getAttribute("managerFirstName");

            String clientLastNameStr = actTag.getAttribute("clientLastName");

            String clientFirstNameStr = actTag.getAttribute("clientFirstName");

            String dateStr = actTag.getAttribute("date");
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
            LocalDate date = LocalDate.parse(dateStr, formatter);

            String timeStr = actTag.getAttribute("time");

            String managerIdStr = actTag.getAttribute("managerId");
            Integer managerId = Integer.valueOf(managerIdStr);

            String officeStr = actTag.getAttribute("office");

            String ratingStr = actTag.getAttribute("rating");

            String clientCommentStr = actTag.getAttribute("clientComment");

            String managerCommentStr = actTag.getAttribute("managerComment");

            String phoneNumberStr = actTag.getAttribute("phoneNumber");

            Act act = new Act();
            act.setId(actId);
            act.setFinished(isFinished);
            act.setClientId(clientId);
            act.setManagerLastName(managerLastNameStr);
            act.setManagerFirstName(managerFirstNameStr);
            act.setClientLastName(clientLastNameStr);
            act.setClientFirstName(clientFirstNameStr);
            act.setDate(date);
            act.setTime(timeStr);
            act.setManagerId(managerId);
            act.setOffice(officeStr);
            act.setRating(ratingStr);
            act.setClientComment(clientCommentStr);
            act.setManagerComment(managerCommentStr);
            act.setPhoneNumber(phoneNumberStr);

            allActs.add(act);
        }

        return allActs;
    }

    @Override
    public Act loadById(Integer actId) {
        if (actId == null) {
            throw new InvalidIdException();
        }

        List<Act> allActs = loadAll();
        for (Act act : allActs) {
            if (actId.equals(act.getId())) {
                return act;
            }
        }

        throw new ObjectNotFoundException();
    }

    @Override
    public void save(Act act) {
        Integer actId = act.getId();
        if (actId != null) {
            throw new ObjectAlreadyStoredException();
        }

        XmlDb xmlDb = XmlDb.getDb();

        actId = XmlDb.getDb().getNextIdForTable();
        act.setId(actId);

        Document actDocument = xmlDb.getXmlDocumentForTable(XmlDbTable.Acts);

        Element actTag = actDocument.createElement("act");

        actTag.setAttribute("id", actId.toString());
        actTag.setAttribute("clientId", act.getClientId().toString());
        actTag.setAttribute("isFinished", "false");
        actTag.setAttribute("managerLastName", act.getManagerLastName());
        actTag.setAttribute("managerFirstName", act.getManagerFirstName());
        actTag.setAttribute("clientLastName", act.getClientLastName());
        actTag.setAttribute("clientFirstName", act.getClientFirstName());
        actTag.setAttribute("managerId", act.getManagerId().toString());
        actTag.setAttribute("date", act.getDate().toString());
        actTag.setAttribute("time", act.getTime());
        actTag.setAttribute("office", act.getOffice());
        actTag.setAttribute("rating", act.getRating());
        actTag.setAttribute("clientComment", act.getClientComment());
        actTag.setAttribute("managerComment", act.getManagerComment());
        actTag.setAttribute("phoneNumber", act.getPhoneNumber());

        actDocument.getDocumentElement().appendChild(actTag);

        xmlDb.saveDocumentForTable(XmlDbTable.Acts);

    }

    @Override
    public void update(Act act) {
        Integer actId = act.getId();
        if (actId == null) {
            return;
        }
        Act storedAct = loadById(actId);
        if (storedAct == null) {
            return;
        }

        XmlDb xmlDb = XmlDb.getDb();
        Document actDocument = xmlDb.getXmlDocumentForTable(XmlDbTable.Acts);

        Element actTag = actDocument.createElement("act");

        actTag.setAttribute("id", actId.toString());
        actTag.setAttribute("clientId", act.getClientId().toString());
        actTag.setAttribute("isFinished", act.getFinished().toString());
        actTag.setAttribute("managerLastName", act.getManagerLastName());
        actTag.setAttribute("managerFirstName", act.getManagerFirstName());
        actTag.setAttribute("clientLastName", act.getClientLastName());
        actTag.setAttribute("clientFirstName", act.getClientFirstName());
        actTag.setAttribute("managerId", act.getManagerId().toString());
        actTag.setAttribute("date", act.getDate().toString());
        actTag.setAttribute("time", act.getTime());
        actTag.setAttribute("office", act.getOffice());
        actTag.setAttribute("rating", act.getRating());
        actTag.setAttribute("clientComment", act.getClientComment());
        actTag.setAttribute("managerComment", act.getManagerComment());
        actTag.setAttribute("phoneNumber", act.getPhoneNumber());

        xmlDb.updateDocumentForTable(actId, "act", actTag, XmlDbTable.Acts);

    }

    @Override
    public void delete(Integer actId) {
        if (actId == null) {
            return;
        }
        XmlDb xmlDb = XmlDb.getDb();
        xmlDb.deleteFromDocumentForTable(actId, "act", XmlDbTable.Acts);

    }
}
