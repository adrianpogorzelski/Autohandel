package customer;
import vehicles.Truck;
import vehicles.Vehicle;
import game.Data;

import java.util.ArrayList;

public class Customer {
    /** CUSTOMER SETTINGS **/
    public static final Byte INITIAL_CUSTOMERS = 5;
    public static final Integer CUSTOMER_BUDGET = 200000;
    public static final double CHANCE_TO_BUY_DAMAGED_CAR = 0.05;
    public static final double CHANCE_TO_BUY_DAMAGED_SUSPENSION = 0.2;
    public final int minimumCargoSpace;

    public String name;
    public Integer budget;
    public ArrayList<String> favoriteBrands;
    public Boolean canBuyDamagedCar;
    public Boolean canBuyDamagedSuspension;
    public String interestedIn;

    static String[] names = {"Alina", "Bogdan", "Cecylia", "Dariusz", "Eliza", "Franciszek", "Genowefa", "Henryk", "Irena", "Janusz", "Katarzyna", "Ludwik", "Maria", "Norbert", "Piotr", "Renata", "Sławomir", "Tadeusz", "Urszula", "Włodzimierz", "Zenon"};
    String[] vehicleTypes = {"samochód osobowy", "samochód dostawczy"};

    public Customer() {
        this.name = names[(int) (Math.random() * names.length)];
        this.budget = (int) (Math.random() * CUSTOMER_BUDGET);
        this.favoriteBrands = new ArrayList<>();
        this.favoriteBrands.add(Vehicle.brands[(int) (Math.random() * Vehicle.brands.length)]);
        this.favoriteBrands.add(Vehicle.brands[(int) (Math.random() * Vehicle.brands.length)]);
        while (this.favoriteBrands.get(0).equals(this.favoriteBrands.get(1))) {
            this.favoriteBrands.remove(1);
            this.favoriteBrands.add(Vehicle.brands[(int) (Math.random() * Vehicle.brands.length)]);
        }
        this.canBuyDamagedCar = (Math.random() < CHANCE_TO_BUY_DAMAGED_CAR);
        this.canBuyDamagedSuspension = (Math.random() < CHANCE_TO_BUY_DAMAGED_SUSPENSION);
        this.interestedIn = vehicleTypes[(int) (Math.random() * vehicleTypes.length)];
        this.minimumCargoSpace = (int) (Math.random() * Truck.MAX_CARGO_SPACE);
    }

    /** CUSTOMERS GENERATOR **/
    public static void generateCustomer(int n) {
        for (int i = 0; i < n; i++) {
            Customer newCustomer = new Customer();
            Data.availableCustomers.add(newCustomer);
        }
    }

    /** PRINT DETAILS **/
    public void introduce() {
        System.out.println("\n" + this.name + ", poszukuje " + this.interestedIn + " marki " + this.favoriteBrands.get(0) + " lub " + this.favoriteBrands.get(1));
        if (this.interestedIn == "samochód dostawczy") {
            System.out.println("Minimalna pojemność: " + (Truck.MIN_CARGO_SPACE + this.minimumCargoSpace) + "m3");
        }
        System.out.println("Dysponuje budżetem " + this.budget + "zł");
        if (this.canBuyDamagedCar) {
            System.out.println("Może kupić niesprawny pojazd");
        }
        if (this.canBuyDamagedSuspension) {
            System.out.println("Akceptuje uszkodzone zawieszenie");
        }
    }
}
