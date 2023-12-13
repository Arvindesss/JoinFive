package gui.model;

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

    public List<JFMove> getGameMoves() {
        return gameMoves;
    }
}
