package invernadero;

/**
 * Fase 3 - Tres experimentos para ver qué pasa cuando se usan mal los hilos.
 */
public class Fase3_Experimentos {

    public static void main(String[] args) throws InterruptedException {
        experimentoA();
        experimentoB();
        experimentoC();
    }

    /** A: lanzar un sensor e imprimir el informe sin esperar. */
    private static void experimentoA() throws InterruptedException {
        System.out.println("\n===== EXPERIMENTO A: informe sin join() =====");
        Sensor temperatura = new SensorTemperatura();
        Thread hilo = new Thread(temperatura, "sensor-Temperatura");
        hilo.start();

        // main no espera: imprime al instante
        System.out.println("Informe sin esperar  -> " + temperatura.informe());

        hilo.join();
        System.out.println("Informe tras join()  -> " + temperatura.informe());
    }

    /** B: llamar a run() en vez de start(). */
    private static void experimentoB() {
        System.out.println("\n===== EXPERIMENTO B: run() en vez de start() =====");
        ProgramadorRiego riego = new ProgramadorRiego(3);

        long inicio = System.currentTimeMillis();
        riego.run(); // lo ejecuta main, no se crea ningún hilo nuevo
        long fin = System.currentTimeMillis();

        System.out.println("Tiempo que ha tardado riego.run(): " + (fin - inicio) + " ms");
        System.out.println("Estado de riego al final: " + riego.getState());
    }

    /** C: arrancar dos veces el mismo hilo. */
    private static void experimentoC() throws InterruptedException {
        System.out.println("\n===== EXPERIMENTO C: start() dos veces =====");
        ProgramadorRiego riego = new ProgramadorRiego(2);
        riego.start();
        riego.join();
        System.out.println("Estado tras el primer start() y join(): " + riego.getState());

        try {
            riego.start(); // segunda vez sobre el mismo objeto
        } catch (IllegalThreadStateException e) {
            System.out.println("Excepción capturada: " + e);
        }
    }
}
