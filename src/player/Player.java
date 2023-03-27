package player;

import vehicles.Vehicle;

import java.util.ArrayList;
import java.util.LinkedList;

public class Player {
    /** PLAYER SETTINGS **/
    public static final Integer INITIAL_MONEY = 50000;

    public String name;
    public double money;
    public ArrayList<Vehicle> ownedVehicles;
    public LinkedList<String> transactionHistory;


    public Player(String name) {
        this.name = name;
        this.money = INITIAL_MONEY;
        this.ownedVehicles = new ArrayList<>();
        this.transactionHistory = new LinkedList<>();
    }
}
