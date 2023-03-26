package cars;

import java.util.HashMap;

public class Car extends Vehicle {

    public Car() {
        this.mileage = generateMileage();
        this.value = generateValue();
        this.brand = brands[(int) (Math.random() * brands.length)];
        this.color = colors[(int) (Math.random() * colors.length)];
        this.segment = segments[(int) (Math.random() * segments.length)];
        this.workingParts = new HashMap<>();
        this.workingParts.put("brakes", Math.random() > 0.8);
        this.workingParts.put("suspension", Math.random() > 0.8);
        this.workingParts.put("engine", Math.random() > 0.8);
        this.workingParts.put("body", Math.random() > 0.8);
        this.workingParts.put("gearbox", Math.random() > 0.8);
    }

}
