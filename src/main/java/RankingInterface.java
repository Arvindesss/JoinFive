import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RankingInterface extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane gp = new GridPane();
        Label title = new Label("Bonjour");
        gp.add(title,0,0);

        Scene s = new Scene(gp,800,400);

        primaryStage.setScene(s);
        primaryStage.show();
    }
}
