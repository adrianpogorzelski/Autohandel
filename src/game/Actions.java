package game;

import customer.Customer;
import vehicles.Vehicle;

import java.util.Objects;
import java.util.Scanner;

import static game.Data.currentPlayer;

public abstract class Actions {
    static final double INTEREST_RATE = 1.2;
    private static final double TAX_VALUE = 0.02;
    private static final int CAR_WASH_PRICE = 100;

    /**
     * BUY A VEHICLE
     **/
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
            vehicleToBuy = Data.availableVehicles.get(vehicleNumber - 1);
            if (currentPlayer.money < vehicleToBuy.value) {
                System.out.println("Brak wystarczającej ilości gotówki!");
                Menu.showAvailableVehicles();
            } else {
                currentPlayer.ownedVehicles.add(vehicleToBuy);
                currentPlayer.money -= vehicleToBuy.value - carWashAndTax(vehicleToBuy);
                Data.availableVehicles.remove(vehicleToBuy);
                Data.fillVehicleList();
                String receipt = "Kupiono " + vehicleToBuy.color + " " + vehicleToBuy.brand + " " + vehicleToBuy.segment + " za " + vehicleToBuy.value + "zł";
                String additionalCosts = "Opłacono myjnię (" + CAR_WASH_PRICE + "zł) i podatek (" + (int) (vehicleToBuy.value * TAX_VALUE) + "zł)";
                System.out.println(receipt);
                System.out.println(additionalCosts);
                currentPlayer.transactionHistory.add(receipt);
                currentPlayer.transactionHistory.add(additionalCosts);
                endTurn();
            }
        } catch (Exception e) {
            buyVehicle();
        }
    }

    /**
     * SELL A VEHICLE
     **/
    static void sellVehicle() {
        Vehicle vehicleToSell = null;
        // Select vehicle
        if (currentPlayer.ownedVehicles.size() == 0) {
            System.out.println("Brak pojazdów do sprzedania");
            Menu.main();
        } else if (currentPlayer.ownedVehicles.size() == 1) {
            vehicleToSell = currentPlayer.ownedVehicles.get(0);
        } else {
            Scanner playerScanner = new Scanner(System.in);
            System.out.println("Podaj numer pojazdu do sprzedania (1 - " + currentPlayer.ownedVehicles.size() + "): ");
            try {
                int vehicleNumber = playerScanner.nextByte();
                // Number out of range
                if (vehicleNumber <= 0 || vehicleNumber > currentPlayer.ownedVehicles.size()) {
                    sellVehicle();
                }
                // Number in range
                vehicleToSell = Data.availableVehicles.get(vehicleNumber - 1);
            } catch (Exception e) {
                sellVehicle();
            }
        }
        Customer selectedCustomer = selectCustomer();
        if (!customerCanBuy(selectedCustomer, vehicleToSell)) {
            Menu.showOwnedVehicles();
        } else {
            int sellingPrice = (int) (vehicleToSell.value * INTEREST_RATE);
            currentPlayer.money += (sellingPrice - carWashAndTax(vehicleToSell));
            String receipt = "Sprzedano " + vehicleToSell.color + " " + vehicleToSell.brand + " " + vehicleToSell.segment + " za " + sellingPrice + "zł";
            System.out.println(receipt);
            String additionalCosts = "Opłacono myjnię (" + CAR_WASH_PRICE + "zł) i podatek (" + (int) (vehicleToSell.value * TAX_VALUE) + "zł)";
            System.out.println(additionalCosts);
            currentPlayer.transactionHistory.add(receipt);
            currentPlayer.transactionHistory.add(additionalCosts);
            currentPlayer.ownedVehicles.remove(vehicleToSell);
            Data.availableCustomers.remove(selectedCustomer);
            Customer.generateCustomer(2);
            Actions.endTurn();
            Menu.main();
        }
    }

    static Customer selectCustomer() {
        // Select customer
        Menu.printCustomers();
        Customer selectedCustomer = null;
        Scanner playerScanner = new Scanner(System.in);
        System.out.println("Wybierz klienta (1 - " + Data.availableCustomers.size() + "): ");
        try {
            int customerNumber = playerScanner.nextByte();
            // Number out of range
            if (customerNumber <= 0 || customerNumber > Data.availableCustomers.size()) {
                sellVehicle();
            }
            // Number in range
            selectedCustomer = Data.availableCustomers.get(customerNumber - 1);
        } catch (Exception e) {
            sellVehicle();
        }
        return selectedCustomer;
    }

        // Check if customer can buy the vehicle
    static Boolean customerCanBuy(Customer customer, Vehicle vehicle) {
        if (customer.budget < vehicle.value * TAX_VALUE) {
            System.out.println("Klient ma za mały budżet");
            return false;
        } else if (!Objects.equals(customer.interestedIn, vehicle.type)) {
            System.out.println("Klient nie chce tego typu pojazdu");
            return false;
        } else if (!customer.favoriteBrands.contains(vehicle.brand)) {
            System.out.println("Klient nie jest zainteresowany tą marką");
            return false;
        } else if (vehicle.workingParts.containsValue(false)) {
            if (!customer.canBuyDamagedCar) {
                System.out.println("Klient nie chce uszkodzonego pojazdu");
                return false;
            } else {
                return true;
            }
        } else if (!vehicle.workingParts.get("suspension")) {
            if (!customer.canBuyDamagedSuspension) {
                System.out.println("Klient nie chce uszkodzonego pojazdu");
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    /** CAR WASH AND TAX **/
    static Integer carWashAndTax(Vehicle vehicle) {
        return (int) (vehicle.value * TAX_VALUE) + CAR_WASH_PRICE;
    }

    /** END TURN AND CHANGE CURRENT PLAYER **/
    static void endTurn() {
        int playerIndex = Data.players.indexOf(currentPlayer);
        if (Data.players.size() >= 2) {
            // if last player, start again, and make this a new round
            if (playerIndex == Data.players.size() - 1) {
                currentPlayer = Data.players.get(0);
            } else {
                currentPlayer = Data.players.get(playerIndex + 1);
            }
        }
        Menu.main();
    }
}
