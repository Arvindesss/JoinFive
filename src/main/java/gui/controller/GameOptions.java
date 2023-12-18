package gui.controller;

import gui.model.JFGameType;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Class contaning global game parameters
 */
public class GameOptions {

    private static int NB_ROWS = 17;
    private static int NB_COLUMNS = 17;
    private static int TILE_SPACE = 40;

    private static ObjectProperty<JFGameType> GAME_TYPE = new SimpleObjectProperty<>(JFGameType.FIVE_D);

    public static int getNbRows() {
        return NB_ROWS;
    }

    public static int getNbColumns() {
        return NB_COLUMNS;
    }

    public static int getTileSpace() {
        return TILE_SPACE;
    }

    public static JFGameType getGameType() {
        return GAME_TYPE.get();
    }

    public static ObjectProperty<JFGameType> getGameTypeProperty() {
        return GAME_TYPE;
    }

    public static void setGameType(JFGameType gameType) {
        GAME_TYPE.setValue(gameType);
    }
}
