package views.console;

import models.Cell;
import models.Land;
import models.Position;
import models.exceptions.BadPosition;

public class LandView {
    private Land land;

    public LandView(Land land) {
        this.land = land;
    }

    public void displayLand() {
        System.out.print("   ");
        for (int j = 0; j < land.getNbColumns(); j++) {
            System.out.format("%2d ", j + 1);
        }
        System.out.println();
        try {
            for (int i = 0; i < land.getNbLines(); i++) {
                System.out.format("%2d ", i + 1);
                for (int j = 0; j < land.getNbColumns(); j++) {
                    Cell cell = land.getCell(new Position(i, j));
                    if (!cell.isVisible()) {
                        if (cell.hasBomb()) {
                            System.out.print(" _ ");
                        } else {
                            System.out.print(" - ");
                        }
                    } else if (cell.hasBomb()) {
                        System.out.print("***");
                    } else {
                        int nbBombs = land.getNbBombsAround(new Position(i, j));
                        if (nbBombs == 0)
                            System.out.print("   ");
                        else
                            System.out.format("%2d ", nbBombs);
                    }
                }
                System.out.println();
            }
        } catch (BadPosition exception) {
        }
    }

}
