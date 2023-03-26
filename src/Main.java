import cars.Car;
import cars.Truck;
import cars.Vehicle;
import customer.Customer;
import player.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Object> availableVehicles = new ArrayList<>();
    static ArrayList<Customer> availableCustomers = new ArrayList<>();

    /** AVAILABLE CARS GENERATOR **/
    static void generateCar() {
        while (availableVehicles.size() < 12) {
            if (Math.random() < 0.6) {
                Car newCar = new Car();
                availableVehicles.add(newCar);
            } else {
                Truck newTruck = new Truck();
                availableVehicles.add(newTruck);
            }
        }
    }

    /** CUSTOMERS GENERATOR **/
    static void generateCustomer(int n) {
        for (int i = 0; i < n; i++) {
            Customer newCustomer = new Customer();
            availableCustomers.add(newCustomer);
        }
    }

    /** GET PLAYER INPUT **/
    static Byte playerChoice = null;
    public static int getPlayerSelection(int numOptions) {
        Scanner playerInput = new Scanner(System.in);
        System.out.println("Twój ruch: ");
        try {
            playerChoice = playerInput.nextByte();
            if (playerChoice <= 0 || playerChoice > numOptions) {
                getPlayerSelection(numOptions);
            }
        } catch (Exception e) {
            getPlayerSelection(numOptions);
        }
        return playerChoice;
    }

    /** GAME MENU **/
    // Show options
    public static void gameOptions() {
        System.out.println("---");
        System.out.println("1. Przejrzyj pojazdy do kupienia");
        System.out.println("2. Przejrzyj posiadane pojazdy");
        System.out.println("3. Klienci");
        System.out.println("4. Wykup reklamę");
        System.out.println("5. Stan konta");
        System.out.println("6. Historia transakcji");
        System.out.println("---");
        switch (getPlayerSelection(6)) {
            case 1 -> showAvailableCars();
            case 2 -> showOwnedCars();
            case 3 -> showCustomers();
            case 4 -> buyAd();
            case 5 -> showAccountBalance();
            case 6 -> showTransactionHistory();
        }
    }

    /** AVAILABLE CARS MENU **/
    // Print available car list
    static void printAvailableCars() {
        for (int i = 0; i <= availableVehicles.size() - 1; i++) {
            Vehicle currentCar = (Vehicle) availableVehicles.get(i);
            System.out.println("Pojazd " + (i + 1) + ": " + currentCar.color + " " + currentCar.type + " marki " + currentCar.brand + " " + currentCar.segment + ", przebieg " + currentCar.mileage + "km");
            System.out.println("Uszkodzone elementy:" + currentCar.checkParts());
            System.out.println("Cena: " + currentCar.value + "zł");
            System.out.println("---");
        }
    }

    private static void showAvailableCars() {
        System.out.println("---");
        System.out.println("Pojazdy na sprzedaż");
        System.out.println("---");
        printAvailableCars();
        System.out.println("1. Powrót");
        switch (getPlayerSelection(1)) {
            case 1 -> gameOptions();
        }
    }

    private static void showOwnedCars() {
        System.out.println("---");
        System.out.println("Posiadane pojazdy");
        System.out.println("---");
        if (Player.ownedCars != null) {
            for (int i = 0; i < Player.ownedCars.size(); i++) {
                System.out.println(Player.ownedCars.get(i));
            }
        } else {
            System.out.println("Brak posiadanych pojazdów");
        }
        System.out.println("---");
        System.out.println("1. Powrót");
        switch (getPlayerSelection(1)) {
            case 1 -> gameOptions();
        }
    }

    private static void showCustomers() {
        System.out.println("***");
        System.out.println("Dostępni klienci");
        System.out.println("---");
        for (int i = 0; i < availableCustomers.size(); i++) {
            Customer currentCustomer = availableCustomers.get(i);
            System.out.println("Klient " + (i + 1));
            currentCustomer.introduce();
            System.out.println("---");
        }
        System.out.println("---");
        System.out.println("1. Powrót");
        switch (getPlayerSelection(1)) {
            case 1 -> gameOptions();
        }
    }

    private static void buyAd() {
        System.out.println("***");
        System.out.println("Wykup reklamę");
        System.out.println("---");
        System.out.println("1. Powrót");
        switch (getPlayerSelection(1)) {
            case 1 -> gameOptions();
        }
    }

    private static void showAccountBalance() {
        System.out.println("---");
        System.out.println("Stan konta");
        System.out.println("---");
        System.out.println("1. Powrót");
        switch (getPlayerSelection(1)) {
            case 1 -> gameOptions();
        }
    }

    private static void showTransactionHistory() {
        System.out.println("---");
        System.out.println("Historia transakcji");
        System.out.println("---");
        System.out.println("1. Powrót");
        switch (getPlayerSelection(1)) {
            case 1 -> gameOptions();
        }
    }


/*
    static int numPlayers;
*/
/*
    static ArrayList<Player> players = new ArrayList<>();
*/
/*
    static Boolean winCondition = false;
*/

    /*

    // Number of players
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

     */

    public static void main(String[] args) {
        /* GAME SETUP */
        // Generate available cars
        generateCar();
        generateCustomer(5);

        /* GAME */
        System.out.println("*** AAA AUTOHANDEL AAA ***");
        gameOptions();
    }
}
        // Byte numMoves = 1;
        // LinkedList<Object> transactionHistory;



       /* // Get number of players
        getNumPlayers();
        System.out.println("Liczba graczy: " + numPlayers);

        // Create players
        for (int i = 1; i <= numPlayers; i++ ) {
            Scanner newPlayerInput = new Scanner(System.in);
            System.out.println("Podaj imię gracza " + i + ":");
            String newPlayerName = newPlayerInput.nextLine();
            Player newPlayer = new Player(newPlayerName);
            players.add(newPlayer);
            System.out.println("Dodano gracza " + newPlayer.name);
        }*/



        // Begin game
        // Player currentPlayer = players.get(0);

        /*
        while (!winCondition) {

            printMenu();
            Scanner playerInput = new Scanner(System.in);
            Integer playerChoice = playerInput.nextInt();
            switch (playerChoice) {
                case 1:
                    printAvailableCars();
                    break;
            }
            if (numMoves == 3) {
                winCondition = true;
            }
            numMoves++;

            }
        */
