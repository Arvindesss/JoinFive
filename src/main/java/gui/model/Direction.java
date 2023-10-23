package gui.model;

public enum Direction {
    WEST(-1, 0), EAST(1, 0), NORTH(0, 1), SOUTH(0, -1), NORTH_WEST(-1, 1),
    NORTH_EAST(1, 1), SOUTH_WEST(-1, -1), SOUTH_EAST(1, -1);

    private int dx;

    private int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }
}
