package cars;

import java.util.HashMap;
import java.util.Random;

public abstract class Vehicle {
    public Integer value;
    public String brand;
    public Integer mileage;
    public String color;
    public String segment;
    public HashMap<String, Boolean> workingParts;

    static final public String[] brands = {"Omega Romeo", "FLAT", "Luxus", "Poorshe", "Fiord", "Leopard", "FolkWagon"};
    final public String[] colors = {"Brązowy", "Czerwony", "Czarny", "Fioletowy", "Granatowy", "Niebieski", "Pomarańczowy", "Różowy", "Zielony", "Żółty"};
    final public String[] segments = {"Budget", "Standard", "Premium"};

    public Integer generateValue() {
        Random random = new Random();
        int segmentMultiplier = 1;
        if (this.segment.equals("Premium")) {
            segmentMultiplier = 3;
        } else if (this.segment.equals("Standard")) {
            segmentMultiplier = 2;
        }
        return 10000 + random.nextInt(30000) * segmentMultiplier * (1 - this.mileage / 300000);
    }

    public Integer generateMileage() {
        Random random = new Random();
        return random.nextInt(300000);
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
