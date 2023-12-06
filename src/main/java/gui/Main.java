package gui;

import gui.controller.HomeController;
import gui.controller.RankingInterfaceController;
import gui.view.screens.RankingInterface;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, IOException {
        HomeController.launchHomeView();
    }
}
