package views.fx;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.Cell;
import models.Land;
import models.Position;
import models.exceptions.BadPosition;

import java.net.URL;
import java.util.ResourceBundle;

public class LandViewController implements Initializable {
    public static final int SIZE = 30;
    @FXML
    AnchorPane root;
    @FXML
    Label stateLabel;
    @FXML
    Button newGameButton;

    private Land land;
    private ViewListener listener;
    private Button[][] buttons;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        newGameButton.setOnMouseClicked(event -> listener.askNewGame());
    }

    public void setLand(Land land) {
        this.land = land;
    }

    public void setListener(ViewListener listener) {
        this.listener = listener;
    }

    public void setUpLand() {
        EventHandler<MouseEvent> eventHandler = event -> {
            Button button = (Button) event.getSource();
            Position position = (Position) button.getUserData();
            listener.clickOnCell(position);
        };
        buttons = new Button[land.getNbLines()][land.getNbColumns()];
        for (int i = 0; i < land.getNbLines(); i++) {
            for (int j = 0; j < land.getNbColumns(); j++) {
                Button button = new Button();
                setButtonProperties(button, i, j, eventHandler);
                buttons[i][j] = button;
                root.getChildren().add(button);
            }
        }
    }

    private void setButtonProperties(Button button, int i, int j, EventHandler<MouseEvent> eventHandler) {
        button.setPrefWidth(SIZE);
        button.setPrefHeight(SIZE);
        button.setTextOverrun(OverrunStyle.CLIP);
        button.setLayoutX(20 + j * SIZE);
        button.setLayoutY(60 + i * SIZE);
        button.setUserData(new Position(i, j));
        button.setOnMouseClicked(eventHandler);
    }

    public void updateLand() {
        stateLabel.setText("");
        for (int i = 0; i < land.getNbLines(); i++) {
            for (int j = 0; j < land.getNbColumns(); j++) {
                try {
                    updateButtonAt(i, j);
                } catch (BadPosition badPosition) {
                    // impossible d'arriver ici.
                }
            }
        }
    }

    private void updateButtonAt(int i, int j) throws BadPosition {
        Button button = buttons[i][j];
        button.setDisable(false);
        Position position = new Position(i, j);
        Cell cell = land.getCell(position);
        if (!cell.isVisible()) {
            button.setText("");
        } else {
            if (cell.hasBomb()) {
                button.setText("B");
            } else {
                int nbBombsAround = land.getNbBombsAround(position);
                if (nbBombsAround == 0) {
                    button.setText("");
                    button.setDisable(true);
                } else {
                    button.setText(Integer.toString(nbBombsAround));
                }
            }
        }
    }

    public void showBadPositionError() {
        stateLabel.setText("Mauvaise position !!!");
    }

    public void showExploded() {
        stateLabel.setText("Perdu !!!");
    }

    public void showSafe() {
        stateLabel.setText("Yeah !!! Gagné !");
    }

    public interface ViewListener {
        void clickOnCell(Position position);
        void askNewGame();
    }
}
