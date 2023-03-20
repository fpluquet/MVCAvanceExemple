package models.exceptions;

import models.Position;

public class AlreadyVisiblePosition extends BadPosition {

    public AlreadyVisiblePosition(Position position) {
        super(position);
    }
}
