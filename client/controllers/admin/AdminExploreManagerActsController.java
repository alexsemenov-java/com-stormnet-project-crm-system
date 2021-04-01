package com.stormnet.crm.system.client.controllers.admin;

import com.stormnet.crm.system.client.common.AppWindows;
import com.stormnet.crm.system.client.common.Controller;
import com.stormnet.crm.system.client.common.WindowConfigs;
import com.stormnet.crm.system.clientservice.ActService;
import com.stormnet.crm.system.clientservice.factory.ServiceFactory;
import com.stormnet.crm.system.obj.Manager;
import com.stormnet.crm.system.obj.Act;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminExploreManagerActsController implements Controller<Manager>, Initializable {

    private Integer thisManagerId;

    @FXML
    private TableView<Act> allActsTable;

    @FXML
    private CheckBox showThisDayActs;

    @FXML
    public void closeBtnPressed() {
        AppWindows appWindows = AppWindows.getInstance();
        appWindows.hideWindow(WindowConfigs.AdminExploreManagerActsWindow);
    }

    @Override
    public void reloadWindow() throws IOException {
        ActService actService = ServiceFactory.getServiceFactory().getActService();
        List<Act> allActs = actService.loadAll();

        List<Act> myActs = new ArrayList<>();

        for (Act act : allActs) {
            int myId = act.getManagerId();
            LocalDate date = act.getDate();
            if (myId == thisManagerId && showThisDayActs.isSelected() && date.equals(LocalDate.now())) {
                myActs.add(act);
            } else if (myId == thisManagerId && !showThisDayActs.isSelected()) {
                myActs.add(act);
            }
        }

        ObservableList<Act> gridItems = allActsTable.getItems();
        gridItems.clear();
        gridItems.addAll(myActs);
    }

    @Override
    public void setFormObject(Manager object) throws IOException {
        thisManagerId = object.getId();

        ActService actService = ServiceFactory.getServiceFactory().getActService();
        List<Act> allActs = actService.loadAll();

        List<Act> myActs = new ArrayList<>();

        for (Act act : allActs) {
            int managerId = act.getManagerId();
            LocalDate date = act.getDate();
            if (managerId == object.getId() && showThisDayActs.isSelected() && date.equals(LocalDate.now())) {
                myActs.add(act);
            } else if (managerId == object.getId() && !showThisDayActs.isSelected()) {
                myActs.add(act);
            }
        }

        ObservableList<Act> gridItems = allActsTable.getItems();
        gridItems.clear();
        gridItems.addAll(myActs);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableColumn<Act, String> clientColumn = new TableColumn<>("Client");
        clientColumn.setMinWidth(100);
        clientColumn.setCellValueFactory(new AdminExploreManagerActsController.ClientStringFactory());

        allActsTable.getColumns().add(clientColumn);


        TableColumn<Act, String> activeColumn = new TableColumn<>("Status");
        activeColumn.setMinWidth(100);
        activeColumn.setCellValueFactory(new AdminExploreManagerActsController.IsActiveStringFactory());

        allActsTable.getColumns().add(activeColumn);


        TableColumn<Act, GridPane> actionsColumn = new TableColumn<>("Actions");
        actionsColumn.setMinWidth(50);
        actionsColumn.setCellValueFactory(new AdminExploreManagerActsController.ButtonsCellFactory());

        allActsTable.getColumns().add(actionsColumn);
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
            viewBtn.setOnAction(new AdminExploreManagerActsController.ViewActEvent(act));

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
