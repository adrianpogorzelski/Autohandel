package game;

import customer.Customer;

import java.util.Scanner;

import static game.Data.currentPlayer;

public abstract class Menu {

    /** GET PLAYER INPUT **/
    static Byte playerChoice = null;
    public static int getPlayerSelection(int numOptions) {
        Scanner playerInput = new Scanner(System.in);
        System.out.println("[Runda " + Data.round + "] Ruch gracza " + currentPlayer.name + ":");
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
        System.out.println("1. Przejrzyj pojazdy do kupienia");
        System.out.println("2. Przejrzyj posiadane pojazdy");
        System.out.println("3. Klienci");
        System.out.println("4. Stan konta");
        System.out.println("5. Historia transakcji\n");
        switch (getPlayerSelection(5)) {
            case 1 -> showAvailableVehicles();
            case 2 -> showOwnedVehicles();
            case 3 -> showCustomers();
            case 4 -> showAccountBalance();
            case 5 -> showTransactionHistory();
        }
    }

    /** 1. AVAILABLE VEHICLES **/
    public static void showAvailableVehicles() {
        System.out.println("Pojazdy na sprzedaż");
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

    /** 2. OWNED VEHICLES **/
    public static void showOwnedVehicles() {
        System.out.println("Posiadane pojazdy");
        if (currentPlayer.ownedVehicles.size() > 0) {
            for (int i = 0; i < currentPlayer.ownedVehicles.size(); i++) {
                System.out.println("Pojazd " + (i + 1));
                currentPlayer.ownedVehicles.get(i).getVehicleData();
            }
        } else {
            System.out.println("Brak posiadanych pojazdów");
        }
        System.out.println("1. Powrót");
        System.out.println("2. Sprzedaj pojazd");
        System.out.println("3. Napraw pojazd");
        switch (getPlayerSelection(3)) {
            case 1 -> main();
            case 2 -> Actions.sellVehicle();
            case 3 -> Actions.fixVehicle();
        }
    }

    /** 3. CUSTOMERS **/
    private static void showCustomers() {
        System.out.println("Dostępni klienci");
        printCustomers();
        System.out.println("1. Powrót");
        System.out.println("2. Pokaż posiadane pojazdy");
        System.out.println("3. Wykup reklamę");
        switch (getPlayerSelection(3)) {
            case 1 -> main();
            case 2 -> showOwnedVehicles();
            case 3 -> buyAd();
        }
    }

    static void printCustomers() {
        for (int i = 0; i < Data.availableCustomers.size(); i++) {
            Customer currentCustomer = Data.availableCustomers.get(i);
            System.out.println("Klient " + (i + 1));
            currentCustomer.introduce();
        }
    }

    // 4. BUY AN AD
    static void buyAd() {
        System.out.println("Wykup reklamę");
        System.out.println("Ogłoszenie w lokalnej gazecie powoduje dopływ losowej grupy kilku nowych klientów");
        System.out.println("Koszt: 1000zł");
        System.out.println("Reklama w Internecie, która jest tańsza od gazety, ale przynosi jednego nowego potencjalnego klienta");
        System.out.println("Koszt: 200zł");
        System.out.println("1. Powrót");
        System.out.println("2. Ogłoszenie w gazecie");
        System.out.println("3. Reklama w Internecie");
        switch (getPlayerSelection(3)) {
            case 1 -> main();
            case 2 -> Actions.newspaperAd();
            case 3 -> Actions.internetAd();
        }
    }

    /** 4. ACCOUNT BALANCE **/
    private static void showAccountBalance() {
        System.out.println("Stan konta");
        System.out.println(currentPlayer.money + "zł");
        System.out.println("1. Powrót");
        if (getPlayerSelection(1) == 1) {
            main();
        }
    }

    /** 5. TRANSACTION HISTORY **/
    private static void showTransactionHistory() {
        System.out.println("Historia transakcji");
        for (String transaction : currentPlayer.transactionHistory) {
            System.out.println(transaction);
        }
        System.out.println("1. Powrót");
        if (getPlayerSelection(1) == 1) {
            main();
        }
    }
}
