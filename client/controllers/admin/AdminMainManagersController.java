package com.stormnet.crm.system.client.controllers.admin;

import com.stormnet.crm.system.client.common.AppWindows;
import com.stormnet.crm.system.client.common.Controller;
import com.stormnet.crm.system.client.common.WindowConfigs;
import com.stormnet.crm.system.clientservice.PersonService;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminMainManagersController implements Controller<Manager>, Initializable {
    @FXML
    private TableView<Manager> allManagersTable;

    @FXML
    public void allActsMenuSelected() throws IOException {
        AppWindows appWindows = AppWindows.getInstance();
        appWindows.hideWindow(WindowConfigs.AdminMainManagersWindow);
        appWindows.createWindow(WindowConfigs.AdminMainActsWindow);
        appWindows.reloadWindow(WindowConfigs.AdminMainActsWindow);
        appWindows.showWindow(WindowConfigs.AdminMainActsWindow);

    }

    @FXML
    public void allManagersMenuSelected() throws IOException {
        AppWindows appWindows = AppWindows.getInstance();
        appWindows.reloadWindow(WindowConfigs.AdminMainManagersWindow);
    }

    @FXML
    public void allClientsMenuSelected() throws IOException {
        AppWindows appWindows = AppWindows.getInstance();
        appWindows.hideWindow(WindowConfigs.AdminMainManagersWindow);
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
        PersonService<Manager> managerService = ServiceFactory.getServiceFactory().getManagerService();
        List<Manager> allManagers = managerService.loadAll();
        ObservableList<Manager> gridItems = allManagersTable.getItems();
        gridItems.clear();
        gridItems.addAll(allManagers);
    }

    @Override
    public void setFormObject(Manager object) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableColumn<Manager, String> ratingColumn = new TableColumn<>("Rating");
        ratingColumn.setMinWidth(100);
        ratingColumn.setCellValueFactory(new RatingStringFactory());

        allManagersTable.getColumns().add(ratingColumn);

        TableColumn<Manager, GridPane> actionsColumn = new TableColumn<>("Actions");
        actionsColumn.setMinWidth(50);
        actionsColumn.setCellValueFactory(new ButtonsCellFactory());

        allManagersTable.getColumns().add(actionsColumn);
    }

    private class RatingStringFactory implements Callback<TableColumn.CellDataFeatures<Manager, String>, ObservableValue<String>> {

        @Override
        public ObservableValue<String> call(TableColumn.CellDataFeatures<Manager, String> param) {
            ActService actService = ServiceFactory.getServiceFactory().getActService();
            List<Act> allActs = new ArrayList<>();
            try {
                allActs = actService.loadAll();
            } catch (IOException e) {
                e.printStackTrace();
            }
            int totalRateSum = 0;
            int marksCount = 0;
            double myRate = 0;
            for (Act act : allActs) {
                if (!act.getRating().equals("") && act.getManagerId().equals(param.getValue().getId())) {
                    totalRateSum += Integer.valueOf(act.getRating());
                    marksCount++;
                }
            }

            if (marksCount != 0) {
                myRate = (double) totalRateSum / marksCount;
            }

            DecimalFormat ratingAccuracy = new DecimalFormat("#.00");
            String rating = String.valueOf(ratingAccuracy.format(myRate));

            return new ReadOnlyStringWrapper(rating);
        }
    }

    private class ButtonsCellFactory implements Callback<TableColumn.CellDataFeatures<Manager, GridPane>, ObservableValue<GridPane>> {

        @Override
        public ObservableValue<GridPane> call(TableColumn.CellDataFeatures<Manager, GridPane> param) {
            Manager manager = param.getValue();

            Button viewBtn = new Button("view acts");
            viewBtn.setOnAction(new ViewActEvent(manager));

            GridPane panel = new GridPane();
            panel.setHgap(2);
            panel.setVgap(2);
            panel.setPadding(new Insets(2, 2, 2, 2));

            panel.add(viewBtn, 0, 0);

            return new SimpleObjectProperty<>(panel);
        }
    }

    private class ViewActEvent implements EventHandler<ActionEvent> {

        private Manager currentManager;

        public ViewActEvent(Manager manager) {
            this.currentManager = manager;
        }

        @Override
        public void handle(ActionEvent event) {
            AppWindows appWindows = AppWindows.getInstance();

            appWindows.createWindow(WindowConfigs.AdminExploreManagerActsWindow);
            try {
                appWindows.setFormObject(WindowConfigs.AdminExploreManagerActsWindow, currentManager);
            } catch (IOException e) {
                e.printStackTrace();
            }
            appWindows.showWindow(WindowConfigs.AdminExploreManagerActsWindow);
        }
    }
}
