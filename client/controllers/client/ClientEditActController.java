package com.stormnet.crm.system.client.controllers.client;

import com.stormnet.crm.system.client.common.AppWindows;
import com.stormnet.crm.system.client.common.Controller;
import com.stormnet.crm.system.client.common.WindowConfigs;
import com.stormnet.crm.system.clientservice.PersonService;
import com.stormnet.crm.system.clientservice.ActService;
import com.stormnet.crm.system.clientservice.factory.ServiceFactory;
import com.stormnet.crm.system.obj.Act;
import com.stormnet.crm.system.obj.Manager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ClientEditActController implements Controller<Act>, Initializable {

    @FXML
    private Label actId;

    @FXML
    private ComboBox OrderTime;

    @FXML
    private ComboBox<Manager> ChooseManager;

    @FXML
    private DatePicker ChooseDate;

    @FXML
    private TextField clientComment;

    @FXML
    private Label phoneNumber;

    @FXML
    public void timeListGeneration() throws IOException {

        ActService actService = ServiceFactory.getServiceFactory().getActService();
        List<Act> allActs = actService.loadAll();
        List<Act> allActsOfThisManager = new ArrayList<>();

        for (Act act : allActs) {
            int managerID = act.getManagerId();
            if (managerID == ChooseManager.getValue().getId()) {
                allActsOfThisManager.add(act);
            }
        }

        List<Act> allActsOfThisManagerForThisDate = new ArrayList<>();

        for (Act act : allActsOfThisManager) {
            LocalDate date = act.getDate();
            if (date.equals(ChooseDate.getValue())) {
                allActsOfThisManagerForThisDate.add(act);
            }
        }

        ObservableList<String> allTimesAvailable = FXCollections.observableArrayList();

        for (int i = 9; i < 18; i++) {
            allTimesAvailable.add(i + ":00");
        }

        for (Act act : allActsOfThisManagerForThisDate) {
            String time = act.getTime();
            allTimesAvailable.remove(time);
        }

        OrderTime.setItems(allTimesAvailable);

    }

    @FXML
    public void setClientComment(TextField clientComment) {
        this.clientComment = clientComment;
    }

    @FXML
    public void setPhoneNumber (Label phoneNumber) {this.phoneNumber = phoneNumber;}

    @FXML
    public void clearTime() {
        OrderTime.setValue(null);
    }


    @FXML
    public void saveBtnPressed() throws IOException {

        if (ChooseManager.getValue() == null || OrderTime.getValue() == null || ChooseDate.getValue() == null || clientComment.getText() == null)  {
            throw new RuntimeException("Please, fill all parameters.");
        }

        ActService actService = ServiceFactory.getServiceFactory().getActService();
        Act editedAct = actService.loadById(Integer.valueOf(actId.getText()));
        editedAct.setManagerId(ChooseManager.getValue().getId());
        editedAct.setTime(OrderTime.getValue().toString());
        editedAct.setDate(ChooseDate.getValue());
        editedAct.setManagerLastName(ChooseManager.getValue().getLastName());
        editedAct.setManagerFirstName(ChooseManager.getValue().getFirstName());
        editedAct.setOffice(ChooseManager.getValue().getOffice());
        editedAct.setClientComment(clientComment.getText());
        editedAct.setPhoneNumber(phoneNumber.getText());

        actService.update(editedAct);

        AppWindows appWindows = AppWindows.getInstance();
        appWindows.hideWindow(WindowConfigs.ClientEditActWindow);
        appWindows.reloadWindow(WindowConfigs.ClientMainWindow);

    }

    @FXML
    public void cancelBtnPressed() throws IOException {
        AppWindows appWindows = AppWindows.getInstance();
        appWindows.hideWindow(WindowConfigs.ClientEditActWindow);
        appWindows.reloadWindow(WindowConfigs.ClientMainWindow);
    }

    @Override
    public void reloadWindow() throws IOException {
        PersonService<Manager> managerService = ServiceFactory.getServiceFactory().getManagerService();
        List<Manager> allPersons = managerService.loadAll();

        ObservableList<Manager> gridItems = ChooseManager.getItems();
        gridItems.clear();
        gridItems.addAll(allPersons);
    }

    @Override
    public void setFormObject(Act object) throws IOException {
        PersonService<Manager> managerService = ServiceFactory.getServiceFactory().getManagerService();
        Manager manager = managerService.loadById(object.getManagerId());

        actId.setText(object.getId().toString());
        OrderTime.setValue(object.getTime());
        ChooseManager.setValue(manager);
        ChooseDate.setValue(object.getDate());
        clientComment.setText(object.getClientComment());
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ChooseManager.getSelectionModel().selectFirst();
        ChooseManager.setCellFactory(new Callback<ListView<Manager>, ListCell<Manager>>() {
            @Override
            public ListCell<Manager> call(ListView<Manager> l) {
                return new ListCell<Manager>() {
                    @Override
                    protected void updateItem(Manager manager, boolean empty) {
                        super.updateItem(manager, empty);
                        if (manager == null || empty) {
                            setGraphic(null);
                        } else {
                            setText(manager.getFirstName() + " " + manager.getLastName());
                        }
                    }
                };
            }
        });

        ChooseManager.setConverter(new StringConverter<Manager>() {
            @Override
            public String toString(Manager manager) {
                if (manager == null) {
                    return null;
                } else {
                    return manager.getFirstName() + " " + manager.getLastName();
                }
            }

            @Override
            public Manager fromString(String id) {
                return null;
            }
        });


        ChooseDate.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.getDayOfWeek() == DayOfWeek.SUNDAY || date.getDayOfWeek() == DayOfWeek.SATURDAY || date.isBefore(LocalDate.now()));
            }
        });
        ChooseDate.setEditable(false);


        PersonService<Manager> personService = ServiceFactory.getServiceFactory().getManagerService();
        List<Manager> allPersons = new ArrayList<>();
        try {
            allPersons = personService.loadAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObservableList<Manager> all = (ObservableList<Manager>) FXCollections.observableArrayList(allPersons);
        ChooseManager.setItems(all);
    }
}
