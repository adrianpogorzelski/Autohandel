package customer;
import cars.Vehicle;

import java.util.ArrayList;

public class Customer {
    public String name;
    public Integer budget;
    public ArrayList<String> favoriteBrands;
    Boolean canBuyDamagedCar;
    Boolean canBuyDamagedSuspension;
    String interestedIn;

    static String[] names = {"Alina", "Bogdan", "Cecylia", "Dariusz", "Eliza", "Franciszek", "Genowefa", "Henryk", "Irena", "Janusz", "Katarzyna", "Ludwik", "Maria", "Norbert", "Piotr", "Renata", "Sławomir", "Tadeusz", "Urszula", "Włodzimierz", "Zenon"};
    String[] vehicleTypes = {"samochód osobowy", "samochód dostawczy"};

    public Customer() {
        this.name = names[(int) (Math.random() * names.length)];
        this.budget = (int) (Math.random() * 200000);
        this.favoriteBrands = new ArrayList<>();
        this.favoriteBrands.add(Vehicle.brands[(int) (Math.random() * Vehicle.brands.length)]);
        this.favoriteBrands.add(Vehicle.brands[(int) (Math.random() * Vehicle.brands.length)]);
        while (this.favoriteBrands.get(0).equals(this.favoriteBrands.get(1))) {
            this.favoriteBrands.remove(1);
            this.favoriteBrands.add(Vehicle.brands[(int) (Math.random() * Vehicle.brands.length)]);
        }
        this.canBuyDamagedCar = (Math.random() < 0.05);
        this.canBuyDamagedSuspension = (Math.random() < 0.2);
        this.interestedIn = vehicleTypes[(int) (Math.random() * vehicleTypes.length)];
    }

    public void introduce() {
        System.out.println(this.name + ", poszukuje " + this.interestedIn + " marki " + this.favoriteBrands.get(0) + " lub " + this.favoriteBrands.get(1));
        System.out.println("Dysponuje budżetem " + this.budget + "zł");
        if (this.canBuyDamagedCar) {
            System.out.println("Może kupić niesprawny pojazd");
        }
        if (this.canBuyDamagedSuspension) {
            System.out.println("Akceptuje uszkodzone zawieszenie");
        }
    }
}
