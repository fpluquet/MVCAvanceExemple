package controllers.console;

import models.Land;
import models.Position;
import models.exceptions.AlreadyVisiblePosition;
import models.exceptions.BadPosition;
import views.console.LandView;
import views.console.UserInteractionView;

public class Game {
    public static void main(String[] args) {
        Land land = new Land();

        LandView landView = new LandView(land);
        UserInteractionView uiView = new UserInteractionView();

        do {
            landView.displayLand();
            Position position = uiView.askPosition();
            try {
                land.clearAround(position);
            } catch (AlreadyVisiblePosition exception) {
                System.err.println("Cellule déjà visible !");
            } catch (BadPosition exception) {
                System.err.println("Cellule inexistante !");
            }
        } while (!land.isExploded() && !land.isSafe());
        landView.displayLand();
        if (land.isExploded()) {
            System.out.println("PERDU !");
        } else {
            System.out.println("GAGNE !");
        }

    }
}
