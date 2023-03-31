package actions;

import customer.Customer;
import game.Data;
import game.Menu;
import player.Player;
import vehicles.Truck;
import vehicles.Vehicle;

import java.util.Scanner;

import static actions.CarWashAndTax.carWashAndTax;
import static actions.EndTurn.endTurn;
import static game.Data.currentPlayer;
import static game.Data.round;

public abstract class SellVehicle implements TransactionSettings {
    public static void sellVehicle() {
        Vehicle vehicleToSell = null;
        // Select vehicle
        if (currentPlayer.ownedVehicles.size() == 0) {
            System.out.println("Brak pojazdów do sprzedania");
            Menu.main();
        }
        if (currentPlayer.ownedVehicles.size() == 1) {
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
                vehicleToSell = currentPlayer.ownedVehicles.get(vehicleNumber - 1);
            } catch (Exception e) {
                sellVehicle();
            }
        }
        Customer selectedCustomer = selectCustomer();
        assert vehicleToSell != null;
        if (!customerCanBuy(selectedCustomer, vehicleToSell)) {
            Menu.showOwnedVehicles();
        }
        int sellingPrice = (int) (vehicleToSell.value * INTEREST_RATE);
        currentPlayer.money += (sellingPrice - carWashAndTax(vehicleToSell));
        String receipt = "Sprzedano " + vehicleToSell.color + " " + vehicleToSell.brand + " " + vehicleToSell.segment + " za " + sellingPrice + "zł";
        System.out.println(receipt);
        String additionalCosts = "Opłacono myjnię (" + CAR_WASH_PRICE + "zł) i podatek (" + (int) (vehicleToSell.value * TAX_VALUE) + "zł)";
        System.out.println(additionalCosts);
        if (currentPlayer.money >= Player.INITIAL_MONEY * 2) {
            System.out.println("Gratulacje! " + currentPlayer.name + " wygrywa grę w " + round + " ruchach!");
        } else {
            currentPlayer.transactionHistory.add(receipt);
            currentPlayer.transactionHistory.add(additionalCosts);
            currentPlayer.ownedVehicles.remove(vehicleToSell);
            Data.availableCustomers.remove(selectedCustomer);
            Customer.generateCustomer(2);
            endTurn();
            Menu.main();
        }
    }

    // Select a customer
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

    // Check if selected customer can buy the vehicle
    static Boolean customerCanBuy(Customer customer, Vehicle vehicle) {
        if (customer.budget < vehicle.value * TAX_VALUE) {
            System.out.println("Klient ma za mały budżet\n");
            return false;
        }
        if (customer.interestedIn.contains(vehicle.brand)) {
            System.out.println("Klient nie chce tego typu pojazdu\n");
            return false;
        }
        if (vehicle instanceof Truck) {
            Truck truck = (Truck) vehicle;
            if (customer.interestedIn.equals("samochód dostawczy") && truck.cargoSpace < customer.minimumCargoSpace) {
                System.out.println("Za mała pojemność");
                return false;
            }
        }
        if (!customer.favoriteBrands.contains(vehicle.brand)) {
            System.out.println("Klient nie jest zainteresowany tą marką\n");
            return false;
        }
        if (!vehicle.workingParts.get("zawieszenie") && !customer.canBuyDamagedSuspension) {
            System.out.println("Klient nie chce uszkodzonego pojazdu\n");
            return false;
        }
        if (vehicle.workingParts.containsValue(false) && !customer.canBuyDamagedCar) {
            System.out.println("Klient nie chce uszkodzonego pojazdu\n");
            return false;
        }
        return true;
    }
}
