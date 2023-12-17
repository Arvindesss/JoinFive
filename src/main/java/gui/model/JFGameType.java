package gui.model;

public enum JFGameType {
    FIVE_T, FIVE_D;

    @Override
    public String toString() {
        if (this.ordinal() == 0) {
            return "5T#";
        } else if (this.ordinal() == 1) {
            return "5D#";
        }
        return "";
    }
}
