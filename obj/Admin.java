package com.stormnet.crm.system.obj;

public class Admin extends Person {
    private static final String ADMIN_PASS = "admin";

    public Admin() {
    }

    public static String getAdminPass() {
        return "admin";
    }
}
