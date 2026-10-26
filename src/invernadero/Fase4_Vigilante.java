package invernadero;

/**
 * Fase 4 - El vigilante de estados (reto).
 * Un quinto hilo (demonio) muestra cada 300 ms el estado de los otros cuatro.
 */
public class Fase4_Vigilante {

    public static void main(String[] args) {
        Sensor temperatura = new SensorTemperatura();
        Sensor humedad = new SensorHumedad();
        Sensor luz = new SensorLuz();

        Thread hiloTemperatura = new Thread(temperatura, "sensor-Temperatura");
        Thread hiloHumedad = new Thread(humedad, "sensor-Humedad");
        Thread hiloLuz = new Thread(luz, "sensor-Luz");
        ProgramadorRiego riego = new ProgramadorRiego();

        Thread[] hilos = {hiloTemperatura, hiloHumedad, hiloLuz, riego};

        Thread vigilante = new Thread(new Vigilante(hilos), "vigilante");
        vigilante.setDaemon(true); // antes de start(): la JVM no espera a los demonios

        Log.mostrar("=== FASE 4: VIGILANTE DE ESTADOS ===");
        Log.mostrar("ANTES de arrancar  -> " + estados(hilos));

        vigilante.start();
        for (Thread h : hilos) {
            h.start();
        }

        try {
            for (Thread h : hilos) {
                h.join();
            }
        } catch (InterruptedException e) {
            Log.mostrar("main interrumpido");
        }

        Log.mostrar("DESPUÉS de join()  -> " + estados(hilos));

        System.out.println();
        System.out.println("----- INFORME -----");
        System.out.println(temperatura.informe());
        System.out.println(humedad.informe());
        System.out.println(luz.informe());
        System.out.println(riego.informe());
        System.out.println("¿Vigilante vivo al acabar main? " + vigilante.isAlive() + " (es demonio: " + vigilante.isDaemon() + ")");
        // main termina aquí y, como solo queda el vigilante (demonio), la JVM se cierra
    }

    /** Devuelve en una línea el estado de cada hilo: "nombre=ESTADO". */
    public static String estados(Thread[] hilos) {
        String texto = "";
        for (Thread h : hilos) {
            texto += h.getName() + "=" + h.getState() + "  ";
        }
        return texto;
    }
}
