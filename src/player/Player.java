package player;

import java.util.ArrayList;

public class Player {
    private final Integer DEFAULT_STARTING_MONEY = 50000;
    public Integer money;
    public ArrayList<Object> ownedCars;
    // public String name;

    public Player(String name) {
        // this.name = name;
        this.money = DEFAULT_STARTING_MONEY;
        this.ownedCars = new ArrayList<>();
    }

}
