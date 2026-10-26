package invernadero;

/**
 * Fase 1C - Prototipo secuencial (la versión "lenta").
 * Todo lo ejecuta el hilo main, una tarea detrás de otra, llamando a run() directamente.
 */
public class Fase1_Secuencial {

    public static void main(String[] args) {
        Sensor temperatura = new SensorTemperatura();
        Sensor humedad = new SensorHumedad();
        Sensor luz = new SensorLuz();
        ProgramadorRiego riego = new ProgramadorRiego();

        Log.mostrar("=== FASE 1: VERSIÓN SECUENCIAL ===");
        long inicio = System.currentTimeMillis();

        // run() NO crea hilos: es una llamada normal a un método
        temperatura.run();
        humedad.run();
        luz.run();
        riego.run();

        long fin = System.currentTimeMillis();

        System.out.println();
        System.out.println("----- INFORME -----");
        System.out.println(temperatura.informe());
        System.out.println(humedad.informe());
        System.out.println(luz.informe());
        System.out.println(riego.informe());
        System.out.println("Tiempo total: " + (fin - inicio) + " ms");
    }
}
