package invernadero;

import java.util.concurrent.ThreadLocalRandom;

public class SensorTemperatura extends Sensor {

    public SensorTemperatura() {
        super("Temperatura", "ºC", 400, 18, 30);
    }

    @Override
    public double medir() {
        // Genera entre 15,0 y 35,0 ºC
        return ThreadLocalRandom.current().nextDouble(15.0, 35.0);
    }
}
