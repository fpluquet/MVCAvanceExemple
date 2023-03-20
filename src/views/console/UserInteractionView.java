package views.console;

import models.Position;

import java.util.Scanner;

public class UserInteractionView {
    public Position askPosition() {
        System.out.println("Quelle position (ex: 1 2) ? ");
        Scanner scanner = new Scanner(System.in);
        int line = scanner.nextInt();
        int column = scanner.nextInt();
        return new Position(line - 1, column - 1);
    }
}
