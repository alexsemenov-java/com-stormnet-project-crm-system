package com.stormnet.crm.system.client.controllers.admin;

import com.stormnet.crm.system.client.common.AppWindows;
import com.stormnet.crm.system.client.common.Controller;
import com.stormnet.crm.system.client.common.Session;
import com.stormnet.crm.system.client.common.WindowConfigs;
import com.stormnet.crm.system.clientservice.PersonService;
import com.stormnet.crm.system.clientservice.factory.ServiceFactory;
import com.stormnet.crm.system.obj.Admin;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AdminCabinetController implements Controller {
    @FXML
    private Label id;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private Label login;

    @FXML
    private PasswordField password;

    public void saveBtnPressed() throws IOException {
        if (firstName.getText().equals("") || lastName.getText().equals("") || password.getText().equals("")) {
            throw new RuntimeException("Please, fill all fields.");
        }

        PersonService<Admin> adminService = ServiceFactory.getServiceFactory().getAdminService();
        Admin currentPerson = adminService.loadById(Integer.valueOf(id.getText()));

        currentPerson.setFirstName(firstName.getText());
        currentPerson.setLastName(lastName.getText());
        currentPerson.setPassword(password.getText());

        adminService.update(currentPerson);
        AppWindows.getInstance().hideWindow(WindowConfigs.AdminCabinetWindow);
    }

    public void cancelBtnPressed() {
        AppWindows.getInstance().hideWindow(WindowConfigs.AdminCabinetWindow);

    }

    @Override
    public void reloadWindow() throws IOException {
        PersonService<Admin> adminService = ServiceFactory.getServiceFactory().getAdminService();
        Admin admin = adminService.loadById(Session.getInstance().getPersonId());
        id.setText(admin.getId().toString());
        firstName.setText(admin.getFirstName());
        lastName.setText(admin.getLastName());
        login.setText(admin.getLogin());
        password.setText(admin.getPassword());
    }

    @Override
    public void setFormObject(Object object) {

    }
}
