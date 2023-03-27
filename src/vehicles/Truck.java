package vehicles;

public class Truck extends Vehicle {
    public Integer cargoSpace;
    public Truck() {
       super();
       this.type = "samochód dostawczy";
       this.cargoSpace = (int) (Math.random() * 30);
    }
}
