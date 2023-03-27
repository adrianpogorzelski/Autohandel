package game;

import customer.Customer;

import java.util.Scanner;

public abstract class Menu {

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
            case 1 -> showAvailableVehicles();
            case 2 -> showOwnedVehicles();
            case 3 -> showCustomers();
            case 4 -> buyAd();
            case 5 -> showAccountBalance();
            case 6 -> showTransactionHistory();
        }
    }

    /** 1. AVAILABLE VEHICLES **/
    public static void showAvailableVehicles() {
        System.out.println("---");
        System.out.println("Pojazdy na sprzedaż");
        System.out.println("---");
        for (int i = 0; i <= Data.availableVehicles.size() - 1; i++) {
            System.out.println("Pojazd " + (i + 1));
            (Data.availableVehicles.get(i)).getVehicleData();
        }
        System.out.println("1. Powrót");
        System.out.println("2. Kup pojazd");
        switch (getPlayerSelection(2)) {
            case 1 -> main();
            case 2 -> Actions.buyVehicle();
        }
    }

    /** 2. OWNED CARS **/
    public static void showOwnedVehicles() {
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
        System.out.println("2. Pokaż szczegóły pojazdu");
        System.out.println("3. Sprzedaj pojazd");
        System.out.println("4. Napraw pojazd");
        switch (getPlayerSelection(4)) {
            case 1 -> main();
            case 2 -> System.out.println("2. Pokaż szczegóły pojazdu");
            case 3 -> Actions.sellVehicle();
            case 4 -> System.out.println("4. Napraw pojazd");
        }
    }

    /** 3. CUSTOMERS **/
    private static void showCustomers() {
        System.out.println("***");
        System.out.println("Dostępni klienci");
        System.out.println("---");
        printCustomers();
        System.out.println("---");
        System.out.println("1. Powrót");
        System.out.println("2. Pokaż posiadane pojazdy");
        switch (getPlayerSelection(2)) {
            case 1 -> main();
            case 2 -> showOwnedVehicles();
        }
    }

    static void printCustomers() {
        for (int i = 0; i < Data.availableCustomers.size(); i++) {
            Customer currentCustomer = Data.availableCustomers.get(i);
            System.out.println("Klient " + (i + 1));
            currentCustomer.introduce();
            System.out.println("---");
        }
    }

    /** 4. BUY AN AD **/
    private static void buyAd() {
        System.out.println("***");
        System.out.println("Wykup reklamę");
        System.out.println("Ogłoszenie w lokalnej gazecie powoduje dopływ losowej grupy kilku nowych klientów");
        System.out.println("Koszt: 5000zł");
        System.out.println("---");
        System.out.println("Reklama w Internecie, która jest tańsza od gazety, ale przynosi jednego nowego potencjalnego klienta");
        System.out.println("Koszt: 1000zł");
        System.out.println("---");
        System.out.println("1. Powrót");
        switch (getPlayerSelection(1)) {
            case 1 -> main();
            case 2 -> System.out.println("Ogłoszenie w gazecie");
            case 3 -> System.out.println("Reklama w Internecie");
        }
    }

    /** 5. ACCOUNT BALANCE **/
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

    /** TRANSACTION HISTORY **/
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
