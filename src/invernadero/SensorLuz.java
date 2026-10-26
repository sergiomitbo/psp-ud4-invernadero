package invernadero;

import java.util.concurrent.ThreadLocalRandom;

public class SensorLuz extends Sensor {

    public SensorLuz() {
        super("Luz", "lux", 500, 200, 800);
    }

    @Override
    public double medir() {
        // Genera entre 0 y 1000 lux
        return ThreadLocalRandom.current().nextDouble(0, 1000);
    }
}
