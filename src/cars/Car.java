package cars;

import java.util.HashMap;
import java.util.Random;

public class Car {
    Integer value;
    String brand;
    Integer mileage;
    String color;
    String segment;
    HashMap<String, Boolean> workingParts = new HashMap<>();

    // final public String[] brands = {"Alfa Romeo", "BMW", "Citroen", "Dacia", "Ford", "Honda", "Infiniti", "Jaguar", "Kia", "Lexus", "Mazda", "Nissan", "Opel", "Porsche", "Renault", "Skoda", "Toyota", "Volvo"};
    static final public String[] brands = {"Omega Romeo", "FLAT", "Luxus", "Poorshe", "Fiord", "Leopard", "FolkWagon"};
    final public String[] colors = {"Brązowy", "Czerwony", "Czarny", "Fioletowy", "Granatowy", "Niebieski", "Pomarańczowy", "Różowy", "Zielony", "Żółty"};
    final public String[] segments = {"Budget", "Standard", "Premium"};

    static Integer randomValue(Integer limit) {
        Random random = new Random();
        return random.nextInt(limit);
    }

    public Car() {
        this.value = randomValue(100000);
        this.brand = brands[(int) (Math.random() * brands.length)];
        this.mileage = randomValue(300000);
        this.color = colors[(int) (Math.random() * colors.length)];
        this.segment = segments[(int) (Math.random() * segments.length)];
        this.workingParts.put("brakes", Math.random() > 0.8);
        this.workingParts.put("suspension", Math.random() > 0.8);
        this.workingParts.put("engine", Math.random() > 0.8);
        this.workingParts.put("body", Math.random() > 0.8);
        this.workingParts.put("gearbox", Math.random() > 0.8);
    }

    // Getters
    public double getValue() {
        return value;
    }
    public String getColor() {
        return color;
    }

    public String getBrand() {
        return brand;
    }

    public String getSegment() {
        return segment;
    }

    public double getMileage() {
        return mileage;
    }

    public String checkParts() {
        StringBuilder returnString = new StringBuilder();
        for (String i : workingParts.keySet()) {
            if (!workingParts.containsValue(true)) {
                returnString = new StringBuilder(" Brak");
            } else {
                if (workingParts.get(i)) {
                    switch (i) {
                        case "brakes" -> returnString.append(" - hamulce");
                        case "suspension" -> returnString.append(" - zawieszenie");
                        case "engine" -> returnString.append(" - silnik");
                        case "body" -> returnString.append(" - karoseria");
                        case "gearbox" -> returnString.append(" - skrzynia biegów");
                    }
                }
            }
        }
        return returnString.toString();
    }
}
