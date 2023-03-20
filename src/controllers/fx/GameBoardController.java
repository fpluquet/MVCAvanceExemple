package controllers.fx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import models.Land;
import models.Position;
import models.exceptions.BadPosition;
import views.fx.LandViewController;

import java.util.Optional;

public class GameBoardController implements LandViewController.ViewListener {

    private final Listener listener;
    private Land land;
    private LandViewController viewController;
    private Stage stage;

    public GameBoardController(Stage stage, Listener listener) {
        this.stage = stage;
        this.listener = listener;
    }

    public void show(int lines, int columns, int bombs) throws Exception {
        // Création du modèle
        land = new Land(lines, columns, bombs);

        // Création de la vue
        FXMLLoader loader = new FXMLLoader(LandViewController.class.getResource("LandView.fxml"));
        loader.load();

        viewController = loader.getController();
        viewController.setLand(land);
        viewController.setListener(this);
        viewController.setUpLand();

        Parent root = loader.getRoot();
        this.stage.setScene(new Scene(root));
        this.stage.show();
        stage.setOnCloseRequest((e) -> {
            listener.onBoardClose();
        });


    }

    @Override
    public void clickOnCell(Position position) {
        try {
            if (land.isExploded() || land.isSafe())
                return;
            land.clearAround(position);
            viewController.updateLand();
            if (land.isExploded()) {
                viewController.showExploded();
            } else if (land.isSafe()) {
                viewController.showSafe();
            }
        } catch (BadPosition badPosition) {
            viewController.showBadPositionError();
        }
    }

    @Override
    public void askNewGame() {
        if (land.isExploded() || land.isSafe()) {
            restartGame();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Vous n'avez pas fini la partie.");
            alert.setContentText("Êtes-vous sûr de vouloir quitter ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                restartGame();
            }
        }
    }

    private void restartGame() {
        land.clear();
        viewController.updateLand();
    }

    public interface Listener {
        void onBoardClose();
    }
}
