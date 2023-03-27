package player;

import vehicles.Vehicle;

import java.util.ArrayList;
import java.util.LinkedList;

public class Player {
    public String name;
    public Integer money;
    public ArrayList<Vehicle> ownedVehicles;
    public LinkedList<String> transactionHistory;


    public Player(String name) {
        this.name = name;
        this.money = 50000;
        this.ownedVehicles = new ArrayList<>();
        this.transactionHistory = new LinkedList<>();
    }
}
