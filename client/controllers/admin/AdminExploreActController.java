package com.stormnet.crm.system.client.controllers.admin;

import com.stormnet.crm.system.client.common.AppWindows;
import com.stormnet.crm.system.client.common.Controller;
import com.stormnet.crm.system.client.common.WindowConfigs;
import com.stormnet.crm.system.obj.Act;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class AdminExploreActController implements Controller<Act> {
    @FXML
    private Label actId;

    @FXML
    private Label rating;

    @FXML
    private Label clientFirstName;

    @FXML
    private Label clientLastName;

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
    private Label clientComment;

    @Override
    public void reloadWindow() {

    }

    @Override
    public void setFormObject(Act object) {
        actId.setText(object.getId().toString());
        rating.setText(object.getRating());
        clientComment.setText(object.getClientComment());
        managerComment.setText(object.getManagerComment());
        clientFirstName.setText(object.getClientFirstName());
        clientLastName.setText(object.getClientLastName());
        managerFirstName.setText(object.getManagerFirstName());
        managerLastName.setText(object.getManagerLastName());
        date.setText(object.getDate().toString());
        time.setText(object.getTime());
        office.setText(object.getOffice());
    }

    @FXML
    public void cancelBtnPressed() {
        AppWindows appWindows = AppWindows.getInstance();
        appWindows.hideWindow(WindowConfigs.AdminExploreActWindow);
    }
}


