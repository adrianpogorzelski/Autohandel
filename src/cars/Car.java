package cars;

public class Car {
    double value;
    String brand;
    Integer mileage;
    String color;
    String segment;
    Boolean workingSuspension;
    Object workingParts;

    final public String[] brands = {"Alfa Romeo", "BMW", "Citroen", "Dacia", "Ford", "Honda", "Infiniti", "Jaguar", "Kia", "Lexus", "Mazda", "Nissan", "Opel", "Porsche", "Renault", "Skoda", "Toyota", "Volvo"};

    public Car() {
        this.value = Math.random() * 100000;
        this.brand = brands[(int) (Math.random() * brand.length() - 1)];
    }
}
