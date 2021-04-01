package com.stormnet.crm.system.client.controllers.client;

import com.stormnet.crm.system.client.common.AppWindows;
import com.stormnet.crm.system.client.common.Controller;
import com.stormnet.crm.system.client.common.WindowConfigs;
import com.stormnet.crm.system.clientservice.ActService;
import com.stormnet.crm.system.clientservice.factory.ServiceFactory;
import com.stormnet.crm.system.obj.Act;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientExploreActController implements Controller<Act>, Initializable {
    @FXML
    private Label actId;

    @FXML
    private ComboBox<String> rating;

    @FXML
    private Label managerFirstName;

    @FXML
    private Label managerLastName;

    @FXML
    private Label date;

    @FXML
    private Label managerComment;

    @FXML
    private Label time;

    @FXML
    private Label office;

    @FXML
    private TextArea clientComment;

    @FXML
    private Label phoneNumber;

    @Override
    public void reloadWindow() {

    }

    @Override
    public void setFormObject(Act object) {
        actId.setText(object.getId().toString());
        rating.setValue(object.getRating());
        clientComment.setText(object.getClientComment());
        managerFirstName.setText(object.getManagerFirstName());
        managerLastName.setText(object.getManagerLastName());
        date.setText(object.getDate().toString());
        time.setText(object.getTime());
        office.setText(object.getOffice());
        managerComment.setText(object.getManagerComment());
        phoneNumber.setText(object.getPhoneNumber());
    }

    @FXML
    public void saveBtnPressed() throws IOException {
        ActService actService = ServiceFactory.getServiceFactory().getActService();
        Act act = actService.loadById(Integer.valueOf(actId.getText()));
        act.setClientComment(clientComment.getText());
        act.setRating(rating.getValue());
        actService.update(act);
        AppWindows appWindows = AppWindows.getInstance();
        appWindows.hideWindow(WindowConfigs.ClientExploreActWindow);
        appWindows.reloadWindow(WindowConfigs.ClientMainWindow);
    }

    @FXML
    public void cancelBtnPressed() {
        AppWindows appWindows = AppWindows.getInstance();
        appWindows.hideWindow(WindowConfigs.ClientExploreActWindow);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> allRates = rating.getItems();

        for (int i = 1; i < 6; i++) {
            allRates.add(String.valueOf(i));
        }
    }
}
