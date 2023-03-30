package actions;

import vehicles.Vehicle;

public abstract class CarWashAndTax implements TransactionSettings {
    static Integer carWashAndTax(Vehicle vehicle) {
        return (int) (vehicle.value * TAX_VALUE) + CAR_WASH_PRICE;
    }
}
