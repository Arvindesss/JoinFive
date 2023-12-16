package gui.controller;

import gui.model.JFGameType;

/**
 * Class contaning global game parameters
 */
public class GameOptions {

    private static int NB_ROWS = 17;
    private static int NB_COLUMNS = 17;
    private static int TILE_SPACE = 50;

    private static JFGameType GAME_TYPE = JFGameType.FIVE_D;

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
        return GAME_TYPE;
    }
}
