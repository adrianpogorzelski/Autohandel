package player;

import cars.Vehicle;
import java.util.ArrayList;

public class Player {
    public String name;
    public Integer money;
    public ArrayList<Vehicle> ownedVehicles;

    public Player(String name) {
        this.name = name;
        this.money = 50000;
        this.ownedVehicles = new ArrayList<>();
    }
}
