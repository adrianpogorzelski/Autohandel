package game;

import vehicles.Vehicle;

import java.util.Scanner;

public class Actions {
    /** BUY A VEHICLE **/
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
            if (Data.currentPlayer.money < vehicleToBuy.value) {
                System.out.println("Brak wystarczającej ilości gotówki!");
                Menu.showAvailableVehicles();
            } else {
                Data.currentPlayer.ownedVehicles.add(vehicleToBuy);
                Data.currentPlayer.money -= vehicleToBuy.value - carWashAndTax(vehicleToBuy);
                Data.availableVehicles.remove(vehicleToBuy);
                Vehicle.generateVehicle();
                String receipt = "Kupiono " + vehicleToBuy.color + " " + vehicleToBuy.brand + " " + vehicleToBuy.segment + " za " + vehicleToBuy.value + "zł";
                System.out.println(receipt);
                Data.currentPlayer.transactionHistory.add(receipt);
                endTurn();
            }
        } catch (Exception e) {
            buyVehicle();
        }
    }

    /** CAR WASH AND TAX **/
    static Integer carWashAndTax(Vehicle vehicle) {
        return (int) (vehicle.value * 0.02) + 100;
    }

    /** END TURN AND CHANGE CURRENT PLAYER **/
    static void endTurn() {
        int playerIndex = Data.players.indexOf(Data.currentPlayer);
        if (Data.players.size() >= 2) {
            // if last player, start again, and make this a new round
            if (playerIndex == Data.players.size() - 1) {
                Data.currentPlayer = Data.players.get(0);
            } else {
                Data.currentPlayer = Data.players.get(playerIndex + 1);
            }
        }
        Menu.main();
    }
}
