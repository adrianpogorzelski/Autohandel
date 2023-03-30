package actions;

import static actions.EndTurn.endTurn;

public abstract class FixVehicle {
    public static void fixVehicle() {
        System.out.println("Fixing");
        endTurn();
    }
}
