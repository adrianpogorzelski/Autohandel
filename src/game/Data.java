package game;

import cars.Vehicle;
import customer.Customer;
import player.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class Data {
    public static ArrayList<Vehicle> availableVehicles = new ArrayList<>();
    public static ArrayList<Customer> availableCustomers = new ArrayList<>();
    public static int numPlayers;
    public static ArrayList<Player> players = new ArrayList<>();
    public static Player currentPlayer;
    static Integer round = 1;

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
