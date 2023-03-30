package actions;

import game.Menu;
import vehicles.Vehicle;

import java.util.ArrayList;
import java.util.Scanner;

import static actions.EndTurn.endTurn;
import static game.Data.currentPlayer;

public abstract class FixVehicle {

    static Vehicle vehicleToFix = null;

    public static void fixVehicle() {
        // Select vehicle to fix
        selectVehicle();
        checkIfBroken();
        selectPart();
        selectMechanic();
        System.out.println(">> Wydano XXXXzł na naprawę " + vehicleToFix.color + " " + vehicleToFix.brand);
        endTurn();
    }

    public static void selectVehicle() {
        if (currentPlayer.ownedVehicles.size() == 0) {
            System.out.println("Brak pojazdów do naprawy");
            Menu.main();
        } else if (currentPlayer.ownedVehicles.size() == 1) {
            vehicleToFix = currentPlayer.ownedVehicles.get(0);
        } else {
            Scanner playerScanner = new Scanner(System.in);
            System.out.println("Podaj numer pojazdu do naprawy (1 - " + currentPlayer.ownedVehicles.size() + "): ");
            try {
                int vehicleNumber = playerScanner.nextByte();
                // Number out of range
                if (vehicleNumber <= 0 || vehicleNumber > currentPlayer.ownedVehicles.size()) {
                    fixVehicle();
                }
                // Number in range
                vehicleToFix = currentPlayer.ownedVehicles.get(vehicleNumber - 1);
            } catch (Exception e) {
                fixVehicle();
            }
        }
    }

    static void checkIfBroken() {
        if (!vehicleToFix.workingParts.containsValue(false)) {
            System.out.println("(!) Pojazd nie jest uszkodzony\n");
            Menu.showOwnedVehicles();
        }
    }

    static void selectPart() {
        System.out.println("Wybierz część do naprawy");
        int i = 1;
        ArrayList<String> brokenParts = new ArrayList<>();
        for (String partName : vehicleToFix.workingParts.keySet()) {
            if (!vehicleToFix.workingParts.get(partName)) {
                System.out.println(i + " - " + partName);
                brokenParts.add(partName);
                i++;
            }
        }
        Scanner selectPart = new Scanner(System.in);
        try {
            int selection = selectPart.nextInt();
            if (selection <= 0 || selection > brokenParts.size()) {
                Menu.showOwnedVehicles();
            } else {
                String selectedPart = String.valueOf(brokenParts.get(selection - 1));
                System.out.println(selectedPart);
            }
        } catch (Exception e) {
            Menu.showOwnedVehicles();
        }
    }

    private static void selectMechanic() {
        System.out.println();
    }
}
