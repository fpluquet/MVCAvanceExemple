package models;

import models.exceptions.AlreadyVisiblePosition;
import models.exceptions.BadPosition;

public class Land {
    protected int nbLines = 10;
    protected int nbColumns = 20;
    protected int nbBombs = 5;

    private Cell[][] cells;

    public Land() {
        this(10, 20, 5);
    }

    public int getNbLines() {
        return nbLines;
    }

    public int getNbColumns() {
        return nbColumns;
    }

    public int getNbBombs() {
        return nbBombs;
    }

    public Land(int nbLines, int nbColumns, int nbBombs) {
        setConstants(nbLines, nbColumns, nbBombs);
        cells = new Cell[this.nbLines][this.nbColumns];
        for (int i = 0; i < this.nbLines; i++) {
            for (int j = 0; j < this.nbColumns; j++) {
                cells[i][j] = new Cell();
            }
        }
        setBombs();
    }

    private void setConstants(int nbLines, int nbColumns, int nbBombs) {
        this.nbLines = nbLines;
        this.nbColumns = nbColumns;
        this.nbBombs = nbBombs;
    }

    private void setBombs() {
        for (int i = 0; i < nbBombs; i++) {
            int line, column;
            do {
                line = (int) (Math.random() * nbLines);
                column = (int) (Math.random() * nbColumns);
            } while (cells[line][column].hasBomb());
            cells[line][column].setBomb();
        }
    }

    public Cell getCell(Position position) throws BadPosition {
        checkPosition(position);
        return cells[position.getLine()][position.getColumn()];
    }

    private boolean isIncorrectPosition(Position position) {
        return position.getLine() < 0 || position.getLine() >= nbLines
                || position.getColumn() < 0 || position.getColumn() >= nbColumns;
    }

    private void checkPosition(Position position) throws BadPosition {
        if (isIncorrectPosition(position)) {
            throw new BadPosition(position);
        }
    }

    public void clearAround(Position position) throws BadPosition {
        Cell cell = getCell(position);
        if (cell.isVisible()) {
            throw new AlreadyVisiblePosition(position);
        }
        cell.setVisible();
        if (cell.hasBomb() || this.getNbBombsAround(position) > 0)
            return;
        Position[] positions = new Position[]{
                position.translate(0, 1),
                position.translate(0, -1),
                position.translate(1, 0),
                position.translate(-1, 0)
        };
        for (Position p : positions) {
            if (!isIncorrectPosition(p)) {
                try {
                    clearAround(p);
                } catch (BadPosition e) {

                }
            }
        }
    }


    public int getNbBombsAround(Position position) {
        int nbBombs = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                try {
                    Cell cell = getCell(position.translate(i, j));
                    if (cell.hasBomb()) {
                        nbBombs++;
                    }
                } catch (BadPosition badPosition) {
                }
            }
        }
        return nbBombs;
    }

    public boolean isExploded() {
        for (int i = 0; i < nbLines; i++) {
            for (int j = 0; j < nbColumns; j++) {
                if (cells[i][j].hasBomb() && cells[i][j].isVisible())
                    return true;
            }
        }
        return false;
    }

    public boolean isSafe() {
        for (int i = 0; i < nbLines; i++) {
            for (int j = 0; j < nbColumns; j++) {
                if (!cells[i][j].hasBomb() && !cells[i][j].isVisible())
                    return false;
            }
        }
        return true;
    }

    public void clear() {
        for (int i = 0; i < nbLines; i++) {
            for (int j = 0; j < nbColumns; j++) {
                Cell cell = cells[i][j];
                cell.initialize();
            }
        }
        setBombs();
    }
}
