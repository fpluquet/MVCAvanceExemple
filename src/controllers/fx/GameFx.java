package controllers.fx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import models.Land;
import views.fx.LandViewController;

import java.io.IOException;

public class GameFx extends Application implements GameBoardController.Listener, GameConfigurationController.Listener{


    private Land land;
    private LandViewController viewController;
    private GameConfigurationController confController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        confController = new GameConfigurationController(primaryStage, this);
        try {
            confController.show();
        } catch (Exception e) {
            showStageError("de configuration");
        }
    }

    private void showStageError(String type) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Une erreur s'est produite lorsque la fenêtre " + type +" a du être chargée.");
        alert.setContentText("Ce n'est pas de votre faute mais celle du développeur. Le programme va maintenant s'arrêter.");
        alert.showAndWait();
        Platform.exit();
    }

    @Override
    public void onBoardClose() {
        try {
            confController.show();
        } catch (IOException e) {
            showStageError("de configuration");
        }
    }

    @Override
    public void onNewGameAsked(int lines, int columns, int bombs) {
        confController.hide();
        Stage stage = new Stage();
        GameBoardController boardController = new GameBoardController(stage, this);
        try {
            boardController.show(lines, columns, bombs);
        } catch (Exception e) {
            showStageError("de jeu");
        }

    }
}
