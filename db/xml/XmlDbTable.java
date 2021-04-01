package com.stormnet.crm.system.db.xml;

public enum XmlDbTable {

    Acts("db/acts.xml"),
    Clients("db/clients.xml"),
    Admins("db/admins.xml"),
    Managers("db/managers.xml"),
    Settings("settings/settings.xml");

    private String xmlFilePath;

    XmlDbTable(String xmlFilePath) {
        this.xmlFilePath = xmlFilePath;
    }

    public String getXmlFilePath() {
        return this.xmlFilePath;
    }
}
