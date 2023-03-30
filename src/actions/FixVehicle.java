package actions;

import game.Menu;
import vehicles.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static actions.EndTurn.endTurn;
import static game.Data.currentPlayer;

public abstract class FixVehicle {

    private static Vehicle vehicleToFix = null;
    private static String selectedPart = null;
    private static ArrayList<Mechanic> mechanics = new ArrayList<>();
    private static Mechanic selectedMechanic = null;
    private static Integer totalCost = null;

    public static void fixVehicle() {
        selectVehicle();
        checkIfBroken();
        selectPart();
        selectMechanic();
        System.out.println(selectedMechanic.name + " zabiera się za naprawę " + vehicleToFix.color + " " + vehicleToFix.brand);
        calculateCost(selectedMechanic);
        if (Math.random() > selectedMechanic.chanceToFix) {
            System.out.println("Zadanie przerosło mechanika... Konieczna była interwencja profesjonalisty");
            totalCost = totalCost + calculateCost(mechanics.get(0));
        } else {
            vehicleToFix.workingParts.put(selectedPart, true);
        }
        System.out.println(">> Zapłacono " + totalCost + " za naprawę");
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
                selectedPart = String.valueOf(brokenParts.get(selection - 1));
                System.out.println(selectedPart);
            }
        } catch (Exception e) {
            Menu.showOwnedVehicles();
        }
    }

    /** MECHANICS **/
    private static class Mechanic {
        String name;
        String description;
        double priceModifier;
        double chanceToFix;
        double chanceToBreak;

        public Mechanic(String name, String description, double priceModifier, double chanceToFix, double chanceToBreak) {
            this.name = name;
            this.description = description;
            this.priceModifier = priceModifier;
            this.chanceToFix = chanceToFix;
            this.chanceToBreak = chanceToBreak;
        }
    }

    private static void selectMechanic() {
        if (mechanics.size() == 3) {
            mechanics.remove(2);
            mechanics.remove(1);
            mechanics.remove(0);
        }
        Mechanic Janusz = new Mechanic("Janusz", " - Jakość i ceny na wysokim poziomie", 1.2, 1, 0);
        Mechanic Marian = new Mechanic("Marian", " - Może nie najtaniej, ale za to jako-tako", 1, 0.9, 0);
        Mechanic Adrian = new Mechanic("Adrian", " - Zupełnie nie podejrzany zakład w całkowicie normalnej okolicy", 0.8, 0.8, 0.02);
        mechanics.add(Janusz);
        mechanics.add(Marian);
        mechanics.add(Adrian);
        System.out.println("Wybierz mechanika");
        int i = 1;
        for (Mechanic mechanic : mechanics) {
            System.out.println("[" + i + "] " + mechanic.name + mechanic.description);
            i++;
        }
        Scanner selectMechanic = new Scanner(System.in);
        try {
            int selection = selectMechanic.nextInt();
            if (selection <= 0 || selection > mechanics.size()) {
                Menu.showOwnedVehicles();
            } else {
                selectedMechanic = mechanics.get(selection - 1);
                System.out.println(selectedPart);
            }
        } catch (Exception e) {
            Menu.showOwnedVehicles();
        }
    }

    private static Integer calculateCost(Mechanic mechanic) {
        HashMap<String, Integer> repairPrices = new HashMap<>();
        repairPrices.put("hamulce", 100);
        repairPrices.put("zawieszenie", 200);
        repairPrices.put("silnik", 1000);
        repairPrices.put("karoseria", 500);
        repairPrices.put("skrzynia biegów", 500);

        totalCost = (int) (repairPrices.get(selectedPart) * mechanic.priceModifier);
        return totalCost;
    }
}
