package mongoutils.testing;

import org.bson.codecs.pojo.annotations.BsonDiscriminator;

@BsonDiscriminator
public abstract class CarComponent {

    private double modelVersion = 1.0;
    private int quantity = 1;

    public int getQuantity() {
        return quantity;
    }

    public CarComponent setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public double getModelVersion() {
        return modelVersion;
    }

    public CarComponent setModelVersion(double modelVersion) {
        this.modelVersion = modelVersion;
        return this;
    }
}
