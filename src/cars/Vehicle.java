package cars;

import java.util.HashMap;
import java.util.Random;

public abstract class Vehicle {
    public String type;
    public Integer value;
    public String brand;
    public Integer mileage = generateMileage();
    public String color;
    public String segment = segments[(int) (Math.random() * segments.length)];
    public HashMap<String, Boolean> workingParts = new HashMap<>();

    static final public String[] brands = {"Omega Romeo", "FLAT", "Luxus", "Poorshe", "Fiord", "Leopard", "FolkWagon"};
    final public String[] colors = {"Brązowy", "Czerwony", "Czarny", "Fioletowy", "Granatowy", "Niebieski", "Pomarańczowy", "Różowy", "Zielony", "Żółty"};
    final public static String[] segments = {"Budget", "Standard", "Premium"};

    /** CONSTRUCTOR **/
    public Vehicle () {
        type = type;
        value = generateValue();
        brand = brands[(int) (Math.random() * brands.length)];
        mileage = mileage;
        color = colors[(int) (Math.random() * colors.length)];
        segment = segment;
        workingParts.put("brakes", Math.random() > 0.8);
        workingParts.put("suspension", Math.random() > 0.8);
        workingParts.put("engine", Math.random() > 0.8);
        workingParts.put("body", Math.random() > 0.8);
        workingParts.put("gearbox", Math.random() > 0.8);
    }

    /** RANDOM CAR VALUE GENERATOR **/
    public Integer generateValue() {
        Random random = new Random();
        int segmentMultiplier = 1;
        if (segment.equals("Premium")) {
            segmentMultiplier = 3;
        } else if (segment.equals("Standard")) {
            segmentMultiplier = 2;
        }
        return 10000 + random.nextInt(30000) * segmentMultiplier * (1 - this.mileage / 300000);
    }

    /** RANDOM MILEAGE GENERATOR **/
    public Integer generateMileage() {
        Random random = new Random();
        return random.nextInt(300000);
    }

    /** RETURN DESCRIPTIONS OF DAMAGED PARTS **/
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

    /** PRINT VEHICLE DETAILS **/
    public void getVehicleData() {
        System.out.println(color + " " + type + " marki " + brand + " " + segment + ", przebieg " + mileage + "km");
        if (this instanceof Truck) {
            System.out.println("Pojemność: " + ((Truck) this).cargoSpace + "m3");
        }
        System.out.println("Uszkodzone elementy:" + checkParts());
        System.out.println("Cena: " + value + "zł");
        System.out.println("---");
    }
}
