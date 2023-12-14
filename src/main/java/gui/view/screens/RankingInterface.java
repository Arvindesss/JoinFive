package gui.view.screens;

import com.sun.javafx.css.StyleManager;
import gui.controller.HomeController;
import gui.controller.RankingInterfaceController;
import gui.model.Player;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class RankingInterface extends Application {
    private List<Player> players = new ArrayList<>();

    public RankingInterface(List<Player> players) {
        this.players = players;
    }

    public RankingInterface() {

    }

    @Override
    public void start(Stage primaryStage) {
        Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
        StyleManager.getInstance().addUserAgentStylesheet("/css/global.css");
        Font.loadFont(getClass().getResourceAsStream("/css/global.css"), 12);

        StackPane stackPane = new StackPane();

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(30));
        vBox.setSpacing(10);

        Label lblTitle = new Label("2P Rang des joueurs");
        lblTitle.getStyleClass().addAll("title", "white");
        lblTitle.setAlignment(Pos.TOP_CENTER);
        vBox.getChildren().add(lblTitle);

        TableView<Player> tableView = new TableView<>();
        tableView.setMaxSize(400, 400);
        TableColumn<Player, String> playerNameCol = new TableColumn<>("Nom joueur");
        TableColumn<Player, Double> playerScoreCol = new TableColumn<>("Scores");
        playerNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        playerNameCol.setPrefWidth(150);
        playerScoreCol.setCellValueFactory(new PropertyValueFactory<>("score"));
        playerScoreCol.setPrefWidth(150);
        tableView.getColumns().add(playerNameCol);
        tableView.getColumns().add(playerScoreCol);
        tableView.getItems().setAll(this.players);

        vBox.getChildren().add(tableView);

        Button btnReturnToMenu = new Button("Retour au menu");
        btnReturnToMenu.getStyleClass().addAll("bg-blue", "white");
        btnReturnToMenu.setOnAction(event -> {
            HomeController.openHomeView();
            RankingInterfaceController.closeView(primaryStage);
        });

        vBox.getChildren().add(btnReturnToMenu);

        // Charge l'image de fond
        Image backgroundImage = new Image("/img/HD-wallpaper-black.jpg");
        // Créer une ImageView pour afficher l'image
        ImageView imageView = new ImageView(backgroundImage);
        imageView.setViewport(new Rectangle2D(0, 0, 700, 500));

        stackPane.getChildren().add(imageView);
        stackPane.getChildren().add(vBox);

        Scene s = new Scene(stackPane, 500, 500);

        imageView.fitWidthProperty().bind(s.widthProperty());
        imageView.fitHeightProperty().bind(s.heightProperty());

        primaryStage.setScene(s);
        primaryStage.show();
    }
}
