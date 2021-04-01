package com.stormnet.crm.system.client.controllers.admin;

import com.stormnet.crm.system.client.common.AppWindows;
import com.stormnet.crm.system.client.common.Controller;
import com.stormnet.crm.system.client.common.WindowConfigs;
import com.stormnet.crm.system.clientservice.PersonService;
import com.stormnet.crm.system.clientservice.factory.ServiceFactory;
import com.stormnet.crm.system.obj.Client;
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

public class AdminMainClientsController implements Controller<Client>, Initializable {
    @FXML
    private TableView<Client> allClientsTable;

    @FXML
    public void allActsMenuSelected() throws IOException {
        AppWindows appWindows = AppWindows.getInstance();
        appWindows.hideWindow(WindowConfigs.AdminMainClientsWindow);
        appWindows.createWindow(WindowConfigs.AdminMainActsWindow);
        appWindows.reloadWindow(WindowConfigs.AdminMainActsWindow);
        appWindows.showWindow(WindowConfigs.AdminMainActsWindow);

    }

    @FXML
    public void allManagersMenuSelected() throws IOException {
        AppWindows appWindows = AppWindows.getInstance();
        appWindows.hideWindow(WindowConfigs.AdminMainClientsWindow);
        appWindows.createWindow(WindowConfigs.AdminMainManagersWindow);
        appWindows.reloadWindow(WindowConfigs.AdminMainManagersWindow);
        appWindows.showWindow(WindowConfigs.AdminMainManagersWindow);
    }

    @FXML
    public void allClientsMenuSelected() throws IOException {
        AppWindows appWindows = AppWindows.getInstance();
        appWindows.reloadWindow(WindowConfigs.AdminMainClientsWindow);
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
        PersonService<Client> clientService = ServiceFactory.getServiceFactory().getClientService();
        List<Client> allClients = clientService.loadAll();
        ObservableList<Client> gridItems = allClientsTable.getItems();
        gridItems.clear();
        gridItems.addAll(allClients);
    }

    @Override
    public void setFormObject(Client object) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableColumn<Client, GridPane> actionsColumn = new TableColumn<>("Actions");
        actionsColumn.setMinWidth(50);
        actionsColumn.setCellValueFactory(new ButtonsCellFactory());

        allClientsTable.getColumns().add(actionsColumn);
    }

    private class ButtonsCellFactory implements Callback<TableColumn.CellDataFeatures<Client, GridPane>, ObservableValue<GridPane>> {

        @Override
        public ObservableValue<GridPane> call(TableColumn.CellDataFeatures<Client, GridPane> param) {
            Client client = param.getValue();

            Button viewBtn = new Button("view acts");
            viewBtn.setOnAction(new ViewActEvent(client));

            GridPane panel = new GridPane();
            panel.setHgap(2);
            panel.setVgap(2);
            panel.setPadding(new Insets(2, 2, 2, 2));

            panel.add(viewBtn, 0, 0);

            return new SimpleObjectProperty<>(panel);
        }
    }

    private class ViewActEvent implements EventHandler<ActionEvent> {

        private Client currentClient;

        public ViewActEvent(Client client) {
            this.currentClient = client;
        }

        @Override
        public void handle(ActionEvent event) {
            AppWindows appWindows = AppWindows.getInstance();

            appWindows.createWindow(WindowConfigs.AdminExploreClientActsWindow);
            try {
                appWindows.setFormObject(WindowConfigs.AdminExploreClientActsWindow, currentClient);
            } catch (IOException e) {
                e.printStackTrace();
            }
            appWindows.showWindow(WindowConfigs.AdminExploreClientActsWindow);
            try {
                appWindows.reloadWindow(WindowConfigs.AdminExploreClientActsWindow);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
