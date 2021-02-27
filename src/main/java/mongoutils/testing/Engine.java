package mongoutils.testing;

public class Engine extends CarComponent {

    private float horsePower;
    public long hash = 58813991939191L;

    public float getHorsePower() {
        return horsePower;
    }

    public Engine setHorsePower(float horsePower) {
        this.horsePower = horsePower;
        return this;
    }
}
