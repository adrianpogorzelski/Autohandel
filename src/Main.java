import cars.Car;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Object> availableVehicles = new ArrayList<>();

    /** AVAILABLE CARS GENERATOR **/
    static void generateCar() {
        while (availableVehicles.size() < 12) {
            Car newCar = new Car();
            availableVehicles.add(newCar);
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
            case 1:
                showAvailableCars();
                break;
            case 2:
                showOwnedCars();
                break;
            case 3:
                showCustomers();
                break;
            case 4:
                buyAd();
                break;
            case 5:
                showAccountBalance();
                break;
            case 6:
                showTransactionHistory();
                break;
        }
    }

    /** AVAILABLE CARS MENU **/
    // Print available car list
    static void printAvailableCars() {
        for (int i = 0; i <= availableVehicles.size() - 1; i++) {
            Car currentCar = (Car) availableVehicles.get(i);
            System.out.println("Samochód " + (i + 1) + ": " + currentCar.getColor() + " " + currentCar.getBrand() + " " + currentCar.getSegment() + ", przebieg " + (int) currentCar.getMileage() + "km");
            System.out.println("Uszkodzone elementy:" + currentCar.checkParts());
            System.out.println("Cena: " + (int) currentCar.getValue() + "zł");
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
            case 1:
                gameOptions();
                break;
        }
    }

    private static void showOwnedCars() {
        System.out.println("---");
        System.out.println("Posiadane pojazdy");
        System.out.println("---");
        System.out.println("1. Powrót");
        switch (getPlayerSelection(1)) {
            case 1:
                gameOptions();
                break;
        }
    }

    private static void showCustomers() {
        System.out.println("---");
        System.out.println("Dostępni klienci");
        System.out.println("---");
        System.out.println("1. Powrót");
        switch (getPlayerSelection(1)) {
            case 1:
                gameOptions();
                break;
        }
    }

    private static void buyAd() {
        System.out.println("---");
        System.out.println("Wykup reklamę");
        System.out.println("---");
        System.out.println("1. Powrót");
        switch (getPlayerSelection(1)) {
            case 1:
                gameOptions();
                break;
        }
    }

    private static void showAccountBalance() {
        System.out.println("---");
        System.out.println("Stan konta");
        System.out.println("---");
        System.out.println("1. Powrót");
        switch (getPlayerSelection(1)) {
            case 1:
                gameOptions();
                break;
        }
    }

    private static void showTransactionHistory() {
        System.out.println("---");
        System.out.println("Historia transakcji");
        System.out.println("---");
        System.out.println("1. Powrót");
        switch (getPlayerSelection(1)) {
            case 1:
                gameOptions();
                break;
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
        /** GAME SETUP **/
        // Generate available cars
        generateCar();

        /** GAME **/
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
