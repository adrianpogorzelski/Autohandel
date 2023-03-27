package game;

import vehicles.Car;
import vehicles.Truck;
import vehicles.Vehicle;
import customer.Customer;
import player.Player;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Data {
    private static final double TRUCK_CHANCE = 0.6;
    public static ArrayList<Vehicle> availableVehicles = new ArrayList<>();
    public static ArrayList<Customer> availableCustomers = new ArrayList<>();
    public static int numPlayers;
    public static ArrayList<Player> players = new ArrayList<>();
    public static Player currentPlayer;
    static Integer round = 1;

    /** AVAILABLE VEHICLES GENERATOR **/
    public static void fillVehicleList() {
        while (Data.availableVehicles.size() < 12) {
            if (Math.random() < TRUCK_CHANCE) {
                Car newCar = new Car();
                Data.availableVehicles.add(newCar);
            } else {
                Truck newTruck = new Truck();
                Data.availableVehicles.add(newTruck);
            }
        }
    }

    /** DEFINE NUMBER OF PLAYERS **/
    public static void getNumPlayers() {
        Scanner playerScanner = new Scanner(System.in);
        System.out.println("Podaj liczbę graczy (1 - 8): ");
        try {
            numPlayers = playerScanner.nextByte();
            if (numPlayers <= 0 || numPlayers > 8) {
                getNumPlayers();
            }
        } catch (Exception e) {
            getNumPlayers();
        }
    }
}
