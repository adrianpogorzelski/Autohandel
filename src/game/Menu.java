package game;

import actions.TransactionSettings;
import customer.Customer;
import vehicles.Vehicle;

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
        System.out.println("[1] Powrót | [2] Sprzedaj pojazd | [3] Napraw pojazd | [4] Historia napraw | [5] Historia wizyt w myjni");
        switch (getPlayerSelection(5)) {
            case 1 -> main();
            case 2 -> sellVehicle();
            case 3 -> fixVehicle();
            case 4 -> repairHistory();
            case 5 -> carWashHistory();
        }
    }

    // Check repair history and costs
    private static void repairHistory() {
        Vehicle vehicleToCheck = null;
        // Select vehicle
        if (currentPlayer.ownedVehicles.size() == 0) {
            System.out.println("Brak pojazdów do sprawdzenia");
            Menu.main();
        }
        if (currentPlayer.ownedVehicles.size() == 1) {
            vehicleToCheck = currentPlayer.ownedVehicles.get(0);
        } else {
            Scanner playerScanner = new Scanner(System.in);
            System.out.println("Podaj numer pojazdu do sprawdzenia (1 - " + currentPlayer.ownedVehicles.size() + "): ");
            try {
                int vehicleNumber = playerScanner.nextByte();
                // Number out of range
                if (vehicleNumber <= 0 || vehicleNumber > currentPlayer.ownedVehicles.size()) {
                    repairHistory();
                }
                // Number in range
                vehicleToCheck = currentPlayer.ownedVehicles.get(vehicleNumber - 1);
            } catch (Exception e) {
                repairHistory();
            }
        }
        for (String item : vehicleToCheck.repairHistory) {
            System.out.println(item);
        }
        System.out.println("*** Historia napraw ***");
        System.out.println("Łączny koszt: " + vehicleToCheck.totalRepairCost + "zł\n");
        System.out.println("[1] Menu główne | [2] Pokaż posiadane pojazdy");
        switch (getPlayerSelection(2)) {
            case 1 -> main();
            case 2 -> showOwnedVehicles();
        }
    }

    // Car wash history and costs
    private static void carWashHistory() {
        Vehicle vehicleToCheck = null;
        // Select vehicle
        if (currentPlayer.ownedVehicles.size() == 0) {
            System.out.println("Brak pojazdów do sprawdzenia");
            Menu.main();
        }
        if (currentPlayer.ownedVehicles.size() == 1) {
            vehicleToCheck = currentPlayer.ownedVehicles.get(0);
        } else {
            Scanner playerScanner = new Scanner(System.in);
            System.out.println("Podaj numer pojazdu do sprawdzenia (1 - " + currentPlayer.ownedVehicles.size() + "): ");
            try {
                int vehicleNumber = playerScanner.nextByte();
                // Number out of range
                if (vehicleNumber <= 0 || vehicleNumber > currentPlayer.ownedVehicles.size()) {
                    repairHistory();
                }
                // Number in range
                vehicleToCheck = currentPlayer.ownedVehicles.get(vehicleNumber - 1);
            } catch (Exception e) {
                repairHistory();
            }
        }
        System.out.println("*** Koszty wizyt w myjni ***");
        System.out.println("Łączny koszt wizyt w myjni: " + vehicleToCheck.totalCarWashCost + "zł\n");
        System.out.println("[1] Menu główne | [2] Pokaż posiadane pojazdy");
        switch (getPlayerSelection(2)) {
            case 1 -> main();
            case 2 -> showOwnedVehicles();
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
        System.out.println("Koszt: " + TransactionSettings.NEWSPAPER_AD_COST + "zł\n");
        System.out.println("Reklama w Internecie, która jest tańsza od gazety, ale przynosi jednego nowego potencjalnego klienta");
        System.out.println("Koszt: " + TransactionSettings.INTERNET_AD_COST+ "zł\n");
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
