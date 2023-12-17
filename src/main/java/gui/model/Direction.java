package gui.model;

public enum Direction {
    LEFT(-1, 0), RIGHT(1, 0), UP(0, 1), DOWN(0, -1), UP_LEFT(-1, 1),
    UP_RIGHT(1, 1), DOWN_LEFT(-1, -1), DOWN_RIGHT(1, -1);

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

    public Direction getInvertedDirection() {
        for (Direction d : values()) {
            if (d.getDx() * -1 == this.dx && d.getDy() * -1 == this.dy) {
                return d;
            }
        }
        throw new RuntimeException("Invalid Direction");
    }

    public static Direction getDirectionFromCoordinates(final Coordinates c1, final Coordinates c2) {
        int x = c2.getX() - c1.getX();
        int y = c2.getY() - c1.getY();
        return getDirectionFromDXDY(x, y);
    }

    public static Direction getDirectionFromDXDY(int dx, int dy) {
        for (Direction d : values()) {
            if (dx == d.dx && dy == d.dy) {
                return d;
            }
        }
        throw new RuntimeException("Invalid Direction");
    }
}
