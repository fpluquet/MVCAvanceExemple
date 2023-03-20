package views.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.DragEvent;
import javafx.scene.input.ScrollEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class LandViewConfigurationController implements Initializable {

    @FXML
    private Label linesColumnsLabel;

    @FXML
    private Slider columnsScroll;

    @FXML
    private Slider linesScroll;

    @FXML
    private Button goButton;

    @FXML
    private Slider bombsScroll;

    @FXML
    private Label bombsLabel;

    protected ViewListener listener;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        linesScroll.valueProperty().addListener((e) -> {
            linesColumnsLabel.setText((int)linesScroll.getValue() + "x" + (int)columnsScroll.getValue());
        });
        columnsScroll.valueProperty().addListener((e) -> {
            linesColumnsLabel.setText((int)linesScroll.getValue() + "x" + (int)columnsScroll.getValue());
        });
        bombsScroll.valueProperty().addListener((e) -> {
           bombsLabel.setText((int)bombsScroll.getValue() + "");
        });
    }

    @FXML
    void goButtonClicked(ActionEvent event) {
        listener.startGame(
                (int)linesScroll.getValue(),
                (int)columnsScroll.getValue(),
                (int)bombsScroll.getValue());
    }

    public void setListener(ViewListener listener) {
        this.listener = listener;
    }

    public interface ViewListener {
        void startGame(int lines, int columns, int bombs);
    }

}
