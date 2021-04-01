package com.stormnet.crm.system.client.common;

public enum WindowConfigs {
    AdminCabinetWindow("/com/stormnet/crm/system/client/xmlui/admin/admin-cabinet.fxml",
            "Admin Cabinet",
            true,
            700,
            275),

    AdminExploreClientActsWindow("/com/stormnet/crm/system/client/xmlui/admin/admin-explore-client-acts-window.fxml",
            "Acts Of This Client Window",
            true,
            700,
            500),

    AdminExploreManagerActsWindow("/com/stormnet/crm/system/client/xmlui/admin/admin-explore-manager-acts-window.fxml",
            "Acts Of This Manager Window",
            true,
            600,
            500),

    AdminExploreActWindow("/com/stormnet/crm/system/client/xmlui/admin/admin-explore-act-window.fxml",
            "Act Window",
            true,
            630,
            500),

    AdminMainActsWindow("/com/stormnet/crm/system/client/xmlui/admin/admin-main-acts.fxml",
            "Admin Main Window",
            true,
            700,
            300),

    AdminMainClientsWindow("/com/stormnet/crm/system/client/xmlui/admin/admin-main-clients.fxml",
            "Admin Main Window",
            true,
            430,
            300),

    AdminMainManagersWindow("/com/stormnet/crm/system/client/xmlui/admin/admin-main-managers.fxml",
            "Admin Main Window",
            true,
            580,
            275),

    ClientCabinetWindow("/com/stormnet/crm/system/client/xmlui/client/client-cabinet.fxml",
            "Client Cabinet",
            true,
            300,
            275),

    ClientEditActWindow("/com/stormnet/crm/system/client/xmlui/client/client-edit-act.fxml",
            "Edit Act",
            true,
            270,
            275),

    ClientExploreActWindow("/com/stormnet/crm/system/client/xmlui/client/client-explore-act-window.fxml",
            "Act Window",
            true,
            630,
            500),

    ClientGetActWindow("/com/stormnet/crm/system/client/xmlui/client/client-get-act.fxml",
            "Get Act",
            true,
            270,
            275),

    ClientMainWindow("/com/stormnet/crm/system/client/xmlui/client/client-main.fxml",
            "Client Main Window",
            true,
            700,
            300),

    ManagerCabinetWindow("/com/stormnet/crm/system/client/xmlui/manager/manager-cabinet.fxml",
            "Manager Cabinet",
            true,
            260,
            300),

    ManagerExploreActWindow("/com/stormnet/crm/system/client/xmlui/manager/manager-explore-act-window.fxml",
            "Act Window",
            true,
            630,
            500),

    ManagerMainWindow("/com/stormnet/crm/system/client/xmlui/manager/manager-main.fxml",
            "Manager Main Window",
            true,
            600,
            275),

    AdminLoginWindow("/com/stormnet/crm/system/client/xmlui/registration/admin-login-window.fxml",
            "Sign in",
            true,
            300,
            275),

    AdminRegistrationWindow("/com/stormnet/crm/system/client/xmlui/registration/admin-registration-window.fxml",
            "Registration",
            true,
            300,
            275),

    ClientLoginWindow("/com/stormnet/crm/system/client/xmlui/registration/client-login-window.fxml",
            "Sign in",
            true,
            300,
            275),

    ClientRegistrationWindow("/com/stormnet/crm/system/client/xmlui/registration/client-registration-window.fxml",
            "Registration",
            true,
            500,
            375),

    ManagerLoginWindow("/com/stormnet/crm/system/client/xmlui/registration/manager-login-window.fxml",
            "Sign in",
            true,
            300,
            275),

    ManagerRegistrationWindow("/com/stormnet/crm/system/client/xmlui/registration/manager-registration-window.fxml",
            "Registration",
            true,
            300,
            275),

    InvitationWindow("/com/stormnet/crm/system/client/xmlui/registration/invitation-window.fxml",
            "Invitation Window",
            false,
            300,
            200),

    WrongLoginPasswordWindow("/com/stormnet/crm/system/client/xmlui/registration/wrong-login-password.fxml",
            "Authorization Failed!",
            true,
            300,
            100);



    private String xmlUi;

    private String title;

    private boolean modalWindow;

    private int width;

    private int height;

    WindowConfigs(String xmlUi, String title, boolean modalWindow, int width, int height) {
        this.xmlUi = xmlUi;
        this.title = title;
        this.modalWindow = modalWindow;
        this.width = width;
        this.height = height;
    }

    public String getXmlUi() {
        return xmlUi;
    }

    public String getTitle() {
        return title;
    }

    public boolean isModalWindow() {
        return modalWindow;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
