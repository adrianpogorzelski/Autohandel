import cars.Car;
import cars.Truck;
import cars.Vehicle;
import customer.Customer;
import player.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Vehicle> availableVehicles = new ArrayList<>();
    static ArrayList<Customer> availableCustomers = new ArrayList<>();
    static int numPlayers;
    static ArrayList<Player> players = new ArrayList<>();
    static Player currentPlayer;
    static Integer round = 1;

    /** AVAILABLE CARS GENERATOR **/
    static void generateVehicle() {
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
        System.out.println("Ruch gracza " + currentPlayer.name + ": ");
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
        System.out.println("Runda " + round);
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
            System.out.println("Pojazd " + (i + 1));
            (availableVehicles.get(i)).getVehicleData();
        }
    }

    /** BUY A VEHICLE **/
    static void buyVehicle() {
        Vehicle vehicleToBuy;
        Scanner playerScanner = new Scanner(System.in);
        System.out.println("Podaj numer pojazdu do kupienia (1 - 12): ");
        try {
            int vehicleNumber = playerScanner.nextByte();
            // Number out of range
            if (vehicleNumber <= 0 || vehicleNumber > 12) {
                buyVehicle();
            }
            // Number in range
            vehicleToBuy = availableVehicles.get(vehicleNumber - 1);
            if (currentPlayer.money < vehicleToBuy.value) {
                System.out.println("Brak wystarczającej ilości gotówki!");
                showAvailableCars();
            } else {
                currentPlayer.ownedVehicles.add(vehicleToBuy);
                currentPlayer.money -= vehicleToBuy.value;
                availableVehicles.remove(vehicleToBuy);
                generateVehicle();
                String receipt = "Kupiono " + vehicleToBuy.color + " " + vehicleToBuy.brand + " " + vehicleToBuy.segment + " za " + vehicleToBuy.value + "zł";
                System.out.println(receipt);
                currentPlayer.transactionHistory.add(receipt);
                endTurn();
            }
        } catch (Exception e) {
            buyVehicle();
        }
    }

    private static void showAvailableCars() {
        System.out.println("---");
        System.out.println("Pojazdy na sprzedaż");
        System.out.println("---");
        printAvailableCars();
        System.out.println("1. Powrót");
        System.out.println("2. Kup pojazd");
        switch (getPlayerSelection(2)) {
            case 1 -> gameOptions();
            case 2 -> buyVehicle();
        }
    }

    private static void showOwnedCars() {
        System.out.println("---");
        System.out.println("Posiadane pojazdy");
        System.out.println("---");
        if (currentPlayer.ownedVehicles.size() > 0) {
            for (int i = 0; i < currentPlayer.ownedVehicles.size(); i++) {
                System.out.println("Pojazd " + (i + 1));
                currentPlayer.ownedVehicles.get(i).getVehicleData();
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
        System.out.println(currentPlayer.money + "zł");
        System.out.println("---");
        System.out.println("1. Powrót");
        switch (getPlayerSelection(1)) {
            case 1 -> gameOptions();
        }
    }

    private static void showTransactionHistory() {
        System.out.println("---");
        System.out.println("Historia transakcji");
        for (String transaction : currentPlayer.transactionHistory) {
            System.out.println(transaction);
        }
        System.out.println("---");
        System.out.println("1. Powrót");
        switch (getPlayerSelection(1)) {
            case 1 -> gameOptions();
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

    static void endTurn() {
        int playerIndex = players.indexOf(currentPlayer);
        if (players.size() >= 2) {
            // if last player, start again, and make this a new round
            if (playerIndex == players.size() - 1) {
                currentPlayer = players.get(0);
            } else {
                currentPlayer = players.get(playerIndex + 1);
            }
        }
        gameOptions();
    }

    /**** !!!!!!!!! GAME !!!!!!!!! ****/
    public static void main(String[] args) {
        System.out.println("*** AAA AUTOHANDEL ***");

        /** GAME SETUP **/
        // Generate available cars
        generateVehicle();

        // Generate customers
        generateCustomer(5);

        // Generate players
        getNumPlayers();
        System.out.println("Liczba graczy: " + numPlayers);

        //// Create players
        for (int i = 1; i <= numPlayers; i++) {
            Scanner newPlayerInput = new Scanner(System.in);
            System.out.println("Podaj imię gracza " + i + ":");
            String newPlayerName = newPlayerInput.nextLine();
            Player newPlayer = new Player(newPlayerName);
            players.add(newPlayer);
            System.out.println("Dodano gracza " + newPlayer.name);
        }
        currentPlayer = players.get(0);

        /** GAME START **/
        gameOptions();
    }
}
