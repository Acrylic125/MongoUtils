package mongoutils.testing;

import java.util.ArrayList;

public class CarFactory {

    public static Car generateDefault() {
        Car car = new Car();
        car.setComponents(new ArrayList<>());
        return car;
    }

    public static Car getNewSimpleCar() {
        Car car = generateDefault();
        car.setCurrentSpeed(45);
        car.addComponents(new Wheel().setRadius(2).setQuantity(4), new Engine().setQuantity(1).setModelVersion(3));
        car.setCarType(Car.CarType.FAMILY_CAR);
        return car;
    }

    public static Car getNewFancyFamilyCar() {
        Car car = generateDefault();
        car.setCurrentSpeed(60);
        car.addComponents(new Wheel().setRadius(2).setQuantity(4), new Engine().setQuantity(1).setModelVersion(13));
        car.setCarType(Car.CarType.FAMILY_CAR);
        return car;
    }

    public static Car getRaceCar() {
        Car car = generateDefault();
        car.setCurrentSpeed(150);
        car.addComponents(new Wheel().setRadius(1).setQuantity(3), new Engine().setQuantity(1).setModelVersion(31));
        car.setCarType(Car.CarType.RACE_CAR);
        return car;
    }

    public static Car getDeliveryCar() {
        Car car = generateDefault();
        car.setCurrentSpeed(30);
        car.addComponents(new Wheel().setRadius(2).setQuantity(3), new Engine().setQuantity(1).setModelVersion(1));
        car.setCarType(Car.CarType.DELIVERY);
        return car;
    }

}
