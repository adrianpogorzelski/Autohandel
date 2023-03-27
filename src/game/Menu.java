package game;

import cars.Vehicle;
import customer.Customer;

import java.util.Scanner;

public class Menu {

    /** GET PLAYER INPUT **/
    static Byte playerChoice = null;
    public static int getPlayerSelection(int numOptions) {
        Scanner playerInput = new Scanner(System.in);
        System.out.println("Ruch gracza " + Data.currentPlayer.name + ": ");
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


    /** MAIN MENU **/
    public static void main() {
        System.out.println("Runda " + Data.round);
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

    static void printAvailableCars() {
        for (int i = 0; i <= Data.availableVehicles.size() - 1; i++) {
            System.out.println("Pojazd " + (i + 1));
            (Data.availableVehicles.get(i)).getVehicleData();
        }
    }

    public static void showAvailableCars() {
        System.out.println("---");
        System.out.println("Pojazdy na sprzedaż");
        System.out.println("---");
        printAvailableCars();
        System.out.println("1. Powrót");
        System.out.println("2. Kup pojazd");
        switch (getPlayerSelection(2)) {
            case 1 -> main();
            case 2 -> Actions.buyVehicle();
        }
    }

    private static void showOwnedCars() {
        System.out.println("---");
        System.out.println("Posiadane pojazdy");
        System.out.println("---");
        if (Data.currentPlayer.ownedVehicles.size() > 0) {
            for (int i = 0; i < Data.currentPlayer.ownedVehicles.size(); i++) {
                System.out.println("Pojazd " + (i + 1));
                Data.currentPlayer.ownedVehicles.get(i).getVehicleData();
            }
        } else {
            System.out.println("Brak posiadanych pojazdów");
        }
        System.out.println("---");
        System.out.println("1. Powrót");
        switch (getPlayerSelection(1)) {
            case 1 -> main();
        }
    }

    private static void showCustomers() {
        System.out.println("***");
        System.out.println("Dostępni klienci");
        System.out.println("---");
        for (int i = 0; i < Data.availableCustomers.size(); i++) {
            Customer currentCustomer = Data.availableCustomers.get(i);
            System.out.println("Klient " + (i + 1));
            currentCustomer.introduce();
            System.out.println("---");
        }
        System.out.println("---");
        System.out.println("1. Powrót");
        switch (getPlayerSelection(1)) {
            case 1 -> main();
        }
    }

    private static void buyAd() {
        System.out.println("***");
        System.out.println("Wykup reklamę");
        System.out.println("---");
        System.out.println("1. Powrót");
        switch (getPlayerSelection(1)) {
            case 1 -> main();
        }
    }

    private static void showAccountBalance() {
        System.out.println("---");
        System.out.println("Stan konta");
        System.out.println(Data.currentPlayer.money + "zł");
        System.out.println("---");
        System.out.println("1. Powrót");
        switch (getPlayerSelection(1)) {
            case 1 -> main();
        }
    }

    private static void showTransactionHistory() {
        System.out.println("---");
        System.out.println("Historia transakcji");
        for (String transaction : Data.currentPlayer.transactionHistory) {
            System.out.println(transaction);
        }
        System.out.println("---");
        System.out.println("1. Powrót");
        switch (getPlayerSelection(1)) {
            case 1 -> main();
        }
    }
}
