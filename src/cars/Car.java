package cars;

import java.util.HashMap;

public class Car {
    double value;
    String brand;
    double mileage;
    String color;
    String segment;
    Boolean workingSuspension;
    HashMap<String, Boolean> workingParts = new HashMap<>();

    final public String[] brands = {"Alfa Romeo", "BMW", "Citroen", "Dacia", "Ford", "Honda", "Infiniti", "Jaguar", "Kia", "Lexus", "Mazda", "Nissan", "Opel", "Porsche", "Renault", "Skoda", "Toyota", "Volvo"};
    final public String[] colors = {"Brązowy", "Czerwony", "Czarny", "Fioletowy", "Granatowy", "Niebieski", "Pomarańczowy", "Różowy", "Zielony", "Żółty"};
    final public String[] segments = {"Budget", "Standard", "Premium"};

    public Car() {
        this.brand = brands[(int) (Math.random() * brands.length)];
        this.mileage = Math.random() * 100000;
        this.color = colors[(int) (Math.random() * colors.length)];
        this.value = Math.random() * 100000;
        this.segment = segments[(int) (Math.random() * segments.length)];
        this.workingSuspension = Math.random() * 2 > 0.5;
        this.workingParts.put("brakes", Math.random() * 2 > 0.1);
        this.workingParts.put("engine", Math.random() * 2 > 0.1);
        this.workingParts.put("body", Math.random() * 2 > 0.1);
        this.workingParts.put("gearbox", Math.random() * 2 > 0.1);
    }

    public String getColor() {
        return color;
    }
}
