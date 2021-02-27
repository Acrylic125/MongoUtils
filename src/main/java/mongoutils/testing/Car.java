package mongoutils.testing;

import org.bson.codecs.pojo.annotations.BsonId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Car {

    public enum CarType {
        RACE_CAR, DELIVERY, FAMILY_CAR
    }

    private CarType carType;
    private List<CarComponent> components;
    private double travelledDistance = 0;
    private float currentSpeed = 0;
    private Driver currentDriver;
    @BsonId
    public UUID id = UUID.randomUUID();

    public CarType getCarType() {
        return carType;
    }

    public Car setCarType(CarType carType) {
        this.carType = carType;
        return this;
    }

    public List<CarComponent> getComponents() {
        return components;
    }

    public Car setComponents(List<CarComponent> components) {
        this.components = components;
        return this;
    }

    public double getTravelledDistance() {
        return travelledDistance;
    }

    public Car setTravelledDistance(double travelledDistance) {
        this.travelledDistance = travelledDistance;
        return this;
    }

    public float getCurrentSpeed() {
        return currentSpeed;
    }

    public Car setCurrentSpeed(float currentSpeed) {
        this.currentSpeed = currentSpeed;
        return this;
    }

    public Driver getCurrentDriver() {
        return currentDriver;
    }

    public Car setCurrentDriver(Driver currentDriver) {
        this.currentDriver = currentDriver;
        return this;
    }

    public UUID getId() {
        return id;
    }

    public Car setId(UUID id) {
        this.id = id;
        return this;
    }

    public Car addComponents(CarComponent... components) {
        this.components.addAll(Arrays.asList(components));
        return this;
    }

}
