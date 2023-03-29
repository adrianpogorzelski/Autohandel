package vehicles;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public abstract class Vehicle {
    /** VEHICLE SETTINGS **/
    public static final double CHANCE_TO_HAVE_DAMAGED_PART = 0.2;

    // Segment value multipliers
    public static final int STANDARD_SEGMENT_MULTIPLIER = 2;
    public static final int PREMIUM_SEGMENT_MULTIPLIER = 3;

    // Car value
    public static final Integer BASE_VALUE = 5000;
    public static final int MAX_VALUE = 30000;
    public static final Integer MAX_MILEAGE = 300000;

    // Data pools
    static final public String[] brands = {"Omega Romeo", "FLAT", "Luxus", "Poorshe", "Fiord", "Leopard", "FolkWagon"};
    final public String[] colors = {"Brązowy", "Czerwony", "Czarny", "Fioletowy", "Granatowy", "Niebieski", "Pomarańczowy", "Różowy", "Zielony", "Żółty"};
    final public static String[] segments = {"Budget", "Standard", "Premium"};

    /** VARIABLES **/
    public String type;
    public Integer value;
    public String brand;
    public int mileage;
    public String color;
    public String segment = segments[(int) (Math.random() * segments.length)];
    public HashMap<String, Boolean> workingParts = new HashMap<>();

    /** CONSTRUCTOR **/
    public Vehicle () {
        type = type;
        value = generateValue();
        brand = brands[(int) (Math.random() * brands.length)];
        mileage = generateMileage();
        color = colors[(int) (Math.random() * colors.length)];
        segment = segment;
        for (String s : Arrays.asList("hamulce", "zawieszenie", "silnik", "karoseria", "skrzynia biegów")) {
            workingParts.put(s, Math.random() > CHANCE_TO_HAVE_DAMAGED_PART);
        }
    }

    /** RANDOM CAR VALUE GENERATOR **/
    public Integer generateValue() {
        Random random = new Random();
        int segmentMultiplier = 1;
        if (segment.equals("Premium")) {
            segmentMultiplier = PREMIUM_SEGMENT_MULTIPLIER;
        } else if (segment.equals("Standard")) {
            segmentMultiplier = STANDARD_SEGMENT_MULTIPLIER;
        }
        return BASE_VALUE + random.nextInt(MAX_VALUE) * segmentMultiplier * (1 - this.mileage / MAX_MILEAGE);
    }

    /** RANDOM MILEAGE GENERATOR **/
    public Integer generateMileage() {
        Random random = new Random();
        return random.nextInt(MAX_MILEAGE);
    }

    /** RETURN DESCRIPTIONS OF DAMAGED PARTS **/
    public String checkParts() {
        StringBuilder returnString = new StringBuilder();
        for (String key : workingParts.keySet()) {
            if (!workingParts.containsValue(false)) {
                returnString = new StringBuilder(" Brak");
            } else {
                if (!workingParts.get(key)) {
                    returnString.append(" - " + key);
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
