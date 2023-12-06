package gui.model;

import javafx.scene.Group;

import java.util.ArrayList;
import java.util.List;

public class JFGameMoves {

    private List<JFMove> gameMoves;

    public JFGameMoves() {
        this.gameMoves = new ArrayList<>();
    }

    public void addGameMove(JFMove move) {
        this.gameMoves.add(move);
    }

    public void undoMove(Group group) {
        if (!this.gameMoves.isEmpty()) {
            JFMove jfMove = this.gameMoves.get(gameMoves.size() - 1);
            jfMove.getClickedCircle().setOpacity(0);

            //retirer la ligne
            group.getChildren().remove(jfMove.getDrawedLine());
            jfMove.getDrawedLine();

            this.gameMoves.remove(gameMoves.size() - 1);
        }
    }

    public List<JFMove> getGameMoves() {
        return gameMoves;
    }
}
