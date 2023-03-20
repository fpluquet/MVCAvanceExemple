package models.exceptions;

import models.Position;

public class BadPosition extends Exception {
    private Position position;

    public BadPosition(Position position) {
        this.position = position;
    }
}
