package com.stormnet.crm.system.client.controllers.client;

import com.stormnet.crm.system.client.common.AppWindows;
import com.stormnet.crm.system.client.common.Controller;
import com.stormnet.crm.system.client.common.Session;
import com.stormnet.crm.system.client.common.WindowConfigs;
import com.stormnet.crm.system.clientservice.PersonService;
import com.stormnet.crm.system.clientservice.factory.ServiceFactory;
import com.stormnet.crm.system.obj.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ClientCabinetController implements Controller {
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

    @FXML
    private TextField phoneNumber;

    public void saveBtnPressed() throws IOException {
        if (firstName.getText().equals("") || lastName.getText().equals("") || password.getText().equals("") || phoneNumber.getText().equals("")) {
            throw new RuntimeException("Please, fill all fields.");
        }

        PersonService<Client> clientService = ServiceFactory.getServiceFactory().getClientService();
        Client currentPerson = clientService.loadById(Integer.valueOf(id.getText()));

        currentPerson.setFirstName(firstName.getText());
        currentPerson.setLastName(lastName.getText());
        currentPerson.setPassword(password.getText());

        clientService.update(currentPerson);
        AppWindows.getInstance().hideWindow(WindowConfigs.ClientCabinetWindow);
    }

    public void cancelBtnPressed() {
        AppWindows.getInstance().hideWindow(WindowConfigs.ClientCabinetWindow);

    }

    @Override
    public void reloadWindow() throws IOException {
        PersonService<Client> clientService = ServiceFactory.getServiceFactory().getClientService();
        Client client = clientService.loadById(Session.getInstance().getPersonId());
        id.setText(client.getId().toString());
        firstName.setText(client.getFirstName());
        lastName.setText(client.getLastName());
        login.setText(client.getLogin());
        password.setText(client.getPassword());
        phoneNumber.setText(client.getPhoneNumber());
    }

    @Override
    public void setFormObject(Object object) {

    }
}
