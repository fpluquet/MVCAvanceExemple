package controllers.fx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.fx.LandViewConfigurationController;
import views.fx.LandViewController;

import java.io.IOException;

public class GameConfigurationController {

    private final Stage stage;
    private final Listener listener;

    public GameConfigurationController(Stage stage, Listener listener)  {
        this.stage = stage;
        this.listener = listener;
    }
    public void show() throws IOException {
        FXMLLoader loader = new FXMLLoader(LandViewController.class.getResource("LandViewConfiguration.fxml"));
        loader.load();
        LandViewConfigurationController controller = loader.getController();
        controller.setListener(new LandViewConfigurationController.ViewListener() {
            @Override
            public void startGame(int lines, int columns, int bombs) {
                listener.onNewGameAsked(lines, columns, bombs);
            }
        });

        Parent root = loader.getRoot();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void hide() {
        stage.hide();
    }

    public interface Listener {
        void onNewGameAsked(int lines, int columns, int bombs);
    }


}
