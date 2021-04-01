package com.stormnet.crm.system.client.controllers.admin;

import com.stormnet.crm.system.client.common.AppWindows;
import com.stormnet.crm.system.client.common.Controller;
import com.stormnet.crm.system.client.common.WindowConfigs;

import com.stormnet.crm.system.clientservice.ActService;
import com.stormnet.crm.system.clientservice.factory.ServiceFactory;
import com.stormnet.crm.system.obj.Act;
import com.stormnet.crm.system.obj.Client;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import java.util.List;
import java.util.ResourceBundle;

public class AdminExploreClientActsController implements Controller<Client>, Initializable {
    private Integer thisClientId;

    @FXML
    private TableView<Act> allActsTable;

    @FXML
    public void closeBtnPressed() {
        AppWindows appWindows = AppWindows.getInstance();
        appWindows.hideWindow(WindowConfigs.AdminExploreClientActsWindow);
    }

    @Override
    public void reloadWindow() throws IOException {
        ActService actService = ServiceFactory.getServiceFactory().getActService();
        List<Act> allActs = actService.loadAll();

        List<Act> myActs = new ArrayList<>();

        for (Act act : allActs) {
            int clientId = act.getClientId();
            if (clientId == thisClientId) {
                myActs.add(act);
            }
        }

        ObservableList<Act> gridItems = allActsTable.getItems();
        gridItems.clear();
        gridItems.addAll(myActs);
    }

    @Override
    public void setFormObject(Client object) {
        thisClientId = object.getId();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableColumn<Act, String> managerColumn = new TableColumn<>("Manager");
        managerColumn.setMinWidth(100);
        managerColumn.setCellValueFactory(new AdminExploreClientActsController.ManagerStringFactory());

        allActsTable.getColumns().add(managerColumn);

        TableColumn<Act, String> clientColumn = new TableColumn<>("Client");
        clientColumn.setMinWidth(100);
        clientColumn.setCellValueFactory(new AdminExploreClientActsController.ClientStringFactory());

        allActsTable.getColumns().add(clientColumn);


        TableColumn<Act, String> activeColumn = new TableColumn<>("Status");
        activeColumn.setMinWidth(100);
        activeColumn.setCellValueFactory(new AdminExploreClientActsController.IsActiveStringFactory());

        allActsTable.getColumns().add(activeColumn);


        TableColumn<Act, GridPane> actionsColumn = new TableColumn<>("Actions");
        actionsColumn.setMinWidth(50);
        actionsColumn.setCellValueFactory(new AdminExploreClientActsController.ButtonsCellFactory());

        allActsTable.getColumns().add(actionsColumn);
    }

    private class ManagerStringFactory implements Callback<TableColumn.CellDataFeatures<Act, String>, ObservableValue<String>> {

        @Override
        public ObservableValue<String> call(TableColumn.CellDataFeatures<Act, String> param) {
            String manager = param.getValue().getManagerFirstName() + " " + param.getValue().getManagerLastName();
            return new ReadOnlyStringWrapper(manager);
        }
    }

    private class ClientStringFactory implements Callback<TableColumn.CellDataFeatures<Act, String>, ObservableValue<String>> {

        @Override
        public ObservableValue<String> call(TableColumn.CellDataFeatures<Act, String> param) {
            String client = param.getValue().getClientFirstName() + " " + param.getValue().getClientLastName();
            return new ReadOnlyStringWrapper(client);
        }
    }

    private class IsActiveStringFactory implements Callback<TableColumn.CellDataFeatures<Act, String>, ObservableValue<String>> {

        @Override
        public ObservableValue<String> call(TableColumn.CellDataFeatures<Act, String> param) {
            boolean isActive = param.getValue().getFinished();
            String activeAsString;
            if (isActive) {
                activeAsString = "Finished";
            } else {
                activeAsString = "Waiting";
            }

            return new ReadOnlyStringWrapper(activeAsString);
        }
    }

    private class ButtonsCellFactory implements Callback<TableColumn.CellDataFeatures<Act, GridPane>, ObservableValue<GridPane>> {

        @Override
        public ObservableValue<GridPane> call(TableColumn.CellDataFeatures<Act, GridPane> param) {
            Act act = param.getValue();

            Button viewBtn = new Button("view");
            viewBtn.setOnAction(new AdminExploreClientActsController.ViewActEvent(act));

            GridPane panel = new GridPane();
            panel.setHgap(2);
            panel.setVgap(2);
            panel.setPadding(new Insets(2, 2, 2, 2));

            panel.add(viewBtn, 0, 0);

            return new SimpleObjectProperty<>(panel);
        }
    }


    private class ViewActEvent implements EventHandler<ActionEvent> {

        private Act currentAct;

        public ViewActEvent(Act act) {
            this.currentAct = act;
        }

        @Override
        public void handle(ActionEvent event) {
            AppWindows appWindows = AppWindows.getInstance();

            appWindows.createWindow(WindowConfigs.AdminExploreActWindow);
            try {
                appWindows.setFormObject(WindowConfigs.AdminExploreActWindow, currentAct);
            } catch (IOException e) {
                e.printStackTrace();
            }
            appWindows.showWindow(WindowConfigs.AdminExploreActWindow);
        }
    }
}
