package models;

public class Position {

    private int line;
    private int column;

    public Position(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public Position translate(int deltaLine, int deltaColumn) {
        return new Position(line - deltaLine, column - deltaColumn);
    }
}
