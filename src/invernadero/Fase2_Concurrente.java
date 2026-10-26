package invernadero;

/**
 * Fase 2 - El invernadero concurrente.
 * Cada sensor va en su propio Thread y el riego ya es un hilo.
 * main espera con join() a que terminen los cuatro antes de imprimir el informe.
 */
public class Fase2_Concurrente {

    public static void main(String[] args) {
        // Objetos nuevos (no reutilizamos los de la Fase 1)
        Sensor temperatura = new SensorTemperatura();
        Sensor humedad = new SensorHumedad();
        Sensor luz = new SensorLuz();

        // Los sensores son tareas: hay que meterlos en un Thread con nombre
        Thread hiloTemperatura = new Thread(temperatura, "sensor-Temperatura");
        Thread hiloHumedad = new Thread(humedad, "sensor-Humedad");
        Thread hiloLuz = new Thread(luz, "sensor-Luz");

        // El riego ya es un hilo, no hace falta new Thread(...)
        ProgramadorRiego riego = new ProgramadorRiego();

        Log.mostrar("=== FASE 2: VERSIÓN CONCURRENTE ===");
        long inicio = System.currentTimeMillis();

        hiloTemperatura.start();
        hiloHumedad.start();
        hiloLuz.start();
        riego.start();

        // Esperamos a que terminen los cuatro
        try {
            hiloTemperatura.join();
            hiloHumedad.join();
            hiloLuz.join();
            riego.join();
        } catch (InterruptedException e) {
            Log.mostrar("main interrumpido mientras esperaba");
        }

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
