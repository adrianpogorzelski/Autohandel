package actions;

import game.Data;
import game.Menu;
import vehicles.Vehicle;

import java.util.Scanner;

import static actions.CarWashAndTax.carWashAndTax;
import static actions.EndTurn.endTurn;
import static game.Data.currentPlayer;

public abstract class BuyVehicle implements TransactionSettings {
    public static void buyVehicle() {
        Vehicle vehicleToBuy = null;
        Scanner playerScanner = new Scanner(System.in);
        System.out.println("Podaj numer pojazdu do kupienia (1 - 12): ");
        try {
            int vehicleNumber = playerScanner.nextByte();
            // Number out of range
            while (vehicleNumber <= 0 || vehicleNumber > 12) {
                buyVehicle();
            }
            // Number in range
            vehicleToBuy = Data.availableVehicles.get(vehicleNumber - 1);
        } catch (Exception e) {
            buyVehicle();
        }
        if (currentPlayer.money < vehicleToBuy.value) {
            System.out.println("Brak wystarczającej ilości gotówki!");
            Menu.showAvailableVehicles();
        }
        currentPlayer.ownedVehicles.add(vehicleToBuy);
        currentPlayer.money -= vehicleToBuy.value - carWashAndTax(vehicleToBuy);
        Data.availableVehicles.remove(vehicleToBuy);
        Data.fillVehicleList();
        String receipt = "Kupiono " + vehicleToBuy.color + " " + vehicleToBuy.brand + " " + vehicleToBuy.segment + " za " + vehicleToBuy.value + "zł";
        String additionalCosts = "Opłacono myjnię (" + CAR_WASH_PRICE + "zł) i podatek (" + (int) (vehicleToBuy.value * TAX_VALUE) + "zł)";
        System.out.println(">> " + receipt);
        System.out.println(">> " + additionalCosts);
        currentPlayer.transactionHistory.add(receipt);
        currentPlayer.transactionHistory.add(additionalCosts);
        endTurn();
    }
}
