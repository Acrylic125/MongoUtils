package mongoutils.testing;

import org.bson.codecs.pojo.annotations.BsonDiscriminator;

@BsonDiscriminator
public class Wheel extends CarComponent {

    private double radius;

    public double getRadius() {
        return radius;
    }

    public Wheel setRadius(double radius) {
        this.radius = radius;
        return this;
    }
}
