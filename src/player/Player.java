package player;

import java.util.ArrayList;

public class Player {
    public Integer money;
    public static ArrayList<Object> ownedCars;
    public String name;

    public Player(String name) {
        this.name = name;
        this.money = 50000;
        ownedCars = new ArrayList<>();
    }
}
