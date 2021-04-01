package com.stormnet.crm.system;

import com.stormnet.crm.system.client.common.AppWindows;
import com.stormnet.crm.system.client.common.WindowConfigs;
import javafx.stage.Stage;

public class UserApp extends javafx.application.Application {

    @Override
    public void start(Stage primaryStage) {
        AppWindows appWindows = AppWindows.getInstance();
        appWindows.addMainStage(primaryStage);
        appWindows.showWindow(WindowConfigs.InvitationWindow);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

