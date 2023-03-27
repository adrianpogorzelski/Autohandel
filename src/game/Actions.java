package game;

import customer.Customer;
import vehicles.Vehicle;

import java.util.Scanner;

import static game.Data.currentPlayer;

public abstract class Actions {
    static final double interestRate = 1.1;
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
                System.out.println(receipt);
                currentPlayer.transactionHistory.add(receipt);
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
            System.out.println("Klient nie jest zainteresowany tym pojazdem!");
            Menu.showOwnedVehicles();
        } else {
            currentPlayer.money += (int) (vehicleToSell.value * interestRate - carWashAndTax(vehicleToSell));
            String receipt = "Sprzedano " + vehicleToSell.color + " " + vehicleToSell.brand + " " + vehicleToSell.segment + " za " + vehicleToSell.value + "zł";
            System.out.println(receipt);
            currentPlayer.transactionHistory.add(receipt);
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
        return customer.budget >= vehicle.value;
    }

    /** CAR WASH AND TAX **/
    static Integer carWashAndTax(Vehicle vehicle) {
        return (int) (vehicle.value * 0.02) + 100;
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
