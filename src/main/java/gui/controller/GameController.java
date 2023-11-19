package gui.controller;

import gui.view.screens.JoinFiveGridView;
import gui.view.screens.MenuView;
import javafx.stage.Stage;

public class GameController {

    public static void displayGameView() {
        Stage stage1 = new Stage();
        JoinFiveGridView gridView = new JoinFiveGridView(/*new CircleGrid()*/);
        gridView.start(stage1);
        Stage stage2 = new Stage();
        MenuView menu = new MenuView();
        menu.start(stage2);
    }
}
