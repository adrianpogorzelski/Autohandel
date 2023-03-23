import cars.Car;
import player.Player;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    static int numPlayers;
    static ArrayList<Player> players = new ArrayList<>();
    static ArrayList<Object> availableVehicles = new ArrayList<>();
    static Boolean winCondition = false;

    // Number of players
    public static void getNumPlayers() {
        Scanner playerScanner = new Scanner(System.in);
        System.out.println("Podaj liczbę graczy (conajmniej 1): ");
        try {
            numPlayers = playerScanner.nextByte();
            if (numPlayers <= 0 || numPlayers > 8) {
                getNumPlayers();
            }
        } catch (Exception error) {
            getNumPlayers();
        }
    }

    public static void printMenu() {
        System.out.println("1. Przejrzyj pojazdy do kupienia");
        System.out.println("2. Przejrzyj posiadane pojazdy");
        System.out.println("3. Klienci");
        System.out.println("4. Wykup reklamę");
        System.out.println("5. Stan konta");
        System.out.println("6. Historia transakcji");
    }

    // Generate available cars
    static void generateCar() {
        while (availableVehicles.size() < 8) {
            Car newCar = new Car();
            availableVehicles.add(newCar);
        }
    }

    public static void main(String[] args) {
        Byte numMoves = 1;
        LinkedList<Object> transactionHistory;

        System.out.println("*** AAA AUTOHANDEL AAA ***");

        // Get number of players
        getNumPlayers();
        System.out.println("Liczba graczy (1 - 8): " + numPlayers);

        // Create players
        for (int i = 1; i <= numPlayers; i++ ) {
            Scanner newPlayerInput = new Scanner(System.in);
            System.out.println("Podaj imię gracza " + i + ":");
            String newPlayerName = newPlayerInput.nextLine();
            Player newPlayer = new Player(newPlayerName);
            players.add(newPlayer);
            System.out.println("Dodano gracza " + newPlayer.name);
        }

        // Generate available cars
        generateCar();
        for (int i = 0; i <= availableVehicles.size() - 1; i++) {
            Car currentCar = (Car) availableVehicles.get(i);
            System.out.println("Samochód " + (i + 1) + ": " + currentCar.getColor());
        }

        // Begin game
        Player currentPlayer = players.get(0);
        while (winCondition == false) {

            printMenu();
            if (numMoves == 3) {
                winCondition = true;
            }
            numMoves++;
        }
        System.out.println("*** KONIEC GRY! ***");
    }
}