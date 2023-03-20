package models;

public class Cell {
    private boolean isVisible = false;
    private boolean hasBomb = false;


    public boolean isVisible() {
        return isVisible;
    }

    public boolean hasBomb() {
        return hasBomb;
    }

    public void setBomb() {
        hasBomb = true;
    }

    public void setVisible() {
        isVisible = true;
    }

    public void initialize() {
        isVisible = false;
        hasBomb = false;
    }
}
