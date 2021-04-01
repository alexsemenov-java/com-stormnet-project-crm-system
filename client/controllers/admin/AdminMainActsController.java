package com.stormnet.crm.system.client.controllers.admin;

import com.stormnet.crm.system.client.common.AppWindows;
import com.stormnet.crm.system.client.common.Controller;
import com.stormnet.crm.system.client.common.WindowConfigs;
import com.stormnet.crm.system.clientservice.ActService;
import com.stormnet.crm.system.clientservice.factory.ServiceFactory;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminMainActsController implements Controller<Act>, Initializable {
    @FXML
    private TableView<Act> allActsTable;

    @FXML
    public void allActsMenuSelected() throws IOException {
        AppWindows appWindows = AppWindows.getInstance();
        appWindows.reloadWindow(WindowConfigs.AdminMainActsWindow);
    }

    @FXML
    public void allManagersMenuSelected() throws IOException {
        AppWindows appWindows = AppWindows.getInstance();
        appWindows.hideWindow(WindowConfigs.AdminMainActsWindow);
        appWindows.createWindow(WindowConfigs.AdminMainManagersWindow);
        appWindows.reloadWindow(WindowConfigs.AdminMainManagersWindow);
        appWindows.showWindow(WindowConfigs.AdminMainManagersWindow);
    }

    @FXML
    public void allClientsMenuSelected() throws IOException {
        AppWindows appWindows = AppWindows.getInstance();
        appWindows.hideWindow(WindowConfigs.AdminMainActsWindow);
        appWindows.createWindow(WindowConfigs.AdminMainClientsWindow);
        appWindows.reloadWindow(WindowConfigs.AdminMainClientsWindow);
        appWindows.showWindow(WindowConfigs.AdminMainClientsWindow);
    }

    @FXML
    public void personalCabinetBtnPressed() throws IOException {
        AppWindows appWindows = AppWindows.getInstance();
        appWindows.createWindow(WindowConfigs.AdminCabinetWindow);
        appWindows.showWindow(WindowConfigs.AdminCabinetWindow);
        appWindows.reloadWindow(WindowConfigs.AdminCabinetWindow);
    }

    @FXML
    public void exitBtnPressed() {
        System.exit(0);
    }



    @Override
    public void reloadWindow() throws IOException {
        ActService actService = ServiceFactory.getServiceFactory().getActService();
        List<Act> allActs = actService.loadAll();
        ObservableList<Act> gridItems = allActsTable.getItems();
        gridItems.clear();
        gridItems.addAll(allActs);
    }

    @Override
    public void setFormObject(Act object) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableColumn<Act, String> managerColumn = new TableColumn<>("Manager");
        managerColumn.setMinWidth(100);
        managerColumn.setCellValueFactory(new ManagerStringFactory());

        allActsTable.getColumns().add(managerColumn);

        TableColumn<Act, String> clientColumn = new TableColumn<>("Client");
        clientColumn.setMinWidth(100);
        clientColumn.setCellValueFactory(new ClientStringFactory());

        allActsTable.getColumns().add(clientColumn);


        TableColumn<Act, String> activeColumn = new TableColumn<>("Status");
        activeColumn.setMinWidth(100);
        activeColumn.setCellValueFactory(new IsActiveStringFactory());

        allActsTable.getColumns().add(activeColumn);


        TableColumn<Act, GridPane> actionsColumn = new TableColumn<>("Actions");
        actionsColumn.setMinWidth(50);
        actionsColumn.setCellValueFactory(new ButtonsCellFactory());

        allActsTable.getColumns().add(actionsColumn);
    }

    private class ManagerStringFactory implements Callback<TableColumn.CellDataFeatures<Act, String>, ObservableValue<String>> {

        @Override
        public ObservableValue<String> call(TableColumn.CellDataFeatures<Act, String> param) {
            String managerName = param.getValue().getManagerFirstName() + " " + param.getValue().getManagerLastName();
            return new ReadOnlyStringWrapper(managerName);
        }
    }

    private class ClientStringFactory implements Callback<TableColumn.CellDataFeatures<Act, String>, ObservableValue<String>> {

        @Override
        public ObservableValue<String> call(TableColumn.CellDataFeatures<Act, String> param) {
            String clientName = param.getValue().getClientFirstName() + " " + param.getValue().getClientLastName();
            return new ReadOnlyStringWrapper(clientName);
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
            viewBtn.setOnAction(new ViewActEvent(act));

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
