package invernadero;

import java.util.concurrent.ThreadLocalRandom;

public class SensorHumedad extends Sensor {

    public SensorHumedad() {
        super("Humedad", "%", 600, 50, 80);
    }

    @Override
    public double medir() {
        // Genera entre 40,0 y 90,0 %
        return ThreadLocalRandom.current().nextDouble(40.0, 90.0);
    }
}
