package vehicles;

public class Truck extends Vehicle {
    public static final Byte MIN_CARGO_SPACE = 30;
    public static final Byte MAX_CARGO_SPACE = 30;
    public Integer cargoSpace;
    public Truck() {
       super();
       this.type = "samochód dostawczy";
       this.cargoSpace = (int) (Math.random() * MAX_CARGO_SPACE + MIN_CARGO_SPACE);
    }
}
