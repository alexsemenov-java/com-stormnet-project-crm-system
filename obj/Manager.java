package com.stormnet.crm.system.obj;


public class Manager extends Person {
    private static final String MANAGER_PASS = "manager";
    private String office;

    public Manager() {
    }

    public static String getManagerPass() {
        return "manager";
    }

    public String getOffice() {
        return this.office;
    }

    public void setOffice(String office) {
        this.office = office;
    }
}

