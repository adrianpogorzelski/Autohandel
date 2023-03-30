package game;

import customer.Customer;

import java.util.Scanner;

import static actions.BuyAd.internetAd;
import static actions.BuyAd.newspaperAd;
import static actions.BuyVehicle.buyVehicle;
import static actions.FixVehicle.fixVehicle;
import static actions.SellVehicle.sellVehicle;
import static game.Data.currentPlayer;


public abstract class Menu {

    /** GET PLAYER INPUT **/
    static Byte playerChoice = null;
    public static int getPlayerSelection(int numOptions) {
        Scanner playerInput = new Scanner(System.in);
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
        System.out.println("\n*** Runda " + Data.round + " ***");
        System.out.println("Ruch gracza " + currentPlayer.name);
        System.out.println("\n[1] Przejrzyj pojazdy do kupienia | [2] Przejrzyj posiadane pojazdy | [3] Klienci | [4] Stan konta | [5] Historia transakcji");
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
        System.out.println("*** Pojazdy na sprzedaż ***");
        for (int i = 0; i <= Data.availableVehicles.size() - 1; i++) {
            System.out.println("Pojazd " + (i + 1));
            (Data.availableVehicles.get(i)).getVehicleData();
        }
        System.out.println("[1] Powrót | [2] Kup pojazd");
        switch (getPlayerSelection(2)) {
            case 1 -> main();
            case 2 -> buyVehicle();
        }
    }

    /** 2. OWNED CARS **/
    public static void showOwnedVehicles() {
        System.out.println("*** Posiadane pojazdy ***");
        if (currentPlayer.ownedVehicles.size() > 0) {
            for (int i = 0; i < currentPlayer.ownedVehicles.size(); i++) {
                System.out.println("Pojazd " + (i + 1));
                currentPlayer.ownedVehicles.get(i).getVehicleData();
            }
        } else {
            System.out.println("Brak posiadanych pojazdów");
        }
        System.out.println("[1] Powrót | [2] Sprzedaj pojazd | [3] Napraw pojazd");
        switch (getPlayerSelection(3)) {
            case 1 -> main();
            case 2 -> sellVehicle();
            case 3 -> fixVehicle();
        }
    }

    /** 3. CUSTOMERS **/
    private static void showCustomers() {
        System.out.println("Dostępni klienci");
        printCustomers();
        System.out.println("[1] Powrót | [2] Pokaż posiadane pojazdy | [3] Zainwestuj w reklamę");
        switch (getPlayerSelection(3)) {
            case 1 -> main();
            case 2 -> showOwnedVehicles();
            case 3 -> buyAd();
        }
    }

    public static void printCustomers() {
        for (int i = 0; i < Data.availableCustomers.size(); i++) {
            Customer currentCustomer = Data.availableCustomers.get(i);
            System.out.println("Klient " + (i + 1));
            currentCustomer.introduce();
        }
    }

    // 4. BUY AN AD
    public static void buyAd() {
        System.out.println("*** Zainwestuj w reklamę ***\n");
        System.out.println("Ogłoszenie w lokalnej gazecie powoduje dopływ losowej grupy kilku nowych klientów");
        System.out.println("Koszt: 1000zł\n");
        System.out.println("Reklama w Internecie, która jest tańsza od gazety, ale przynosi jednego nowego potencjalnego klienta");
        System.out.println("Koszt: 200zł\n");
        System.out.println("[1] Powrót | [2] Ogłoszenie w gazecie | [3] Reklama w Internecie");
        switch (getPlayerSelection(3)) {
            case 1 -> main();
            case 2 -> newspaperAd();
            case 3 -> internetAd();
        }
    }

    /** 4. ACCOUNT BALANCE **/
    private static void showAccountBalance() {
        System.out.println("*** Stan konta ***");
        System.out.println(currentPlayer.money + "zł\n");
        System.out.println("[1] Powrót");
        switch (getPlayerSelection(1)) {
            case 1 -> main();
        }
    }

    /** 5. TRANSACTION HISTORY **/
    private static void showTransactionHistory() {
        System.out.println("*** Historia transakcji ***");
        for (String transaction : currentPlayer.transactionHistory) {
            System.out.println(transaction);
        }
        System.out.println("\n[1] Powrót");
        switch (getPlayerSelection(1)) {
            case 1 -> main();
        }
    }
}
