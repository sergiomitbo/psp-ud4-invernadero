package invernadero;

/**
 * El vigilante: cada 300 ms muestra el estado de los hilos del invernadero.
 * Tiene un bucle infinito, así que hay que marcar su hilo como demonio
 * para que la JVM no le espere y el programa pueda terminar.
 */
public class Vigilante implements Runnable {

    private static final int PERIODO_MS = 300;
    private Thread[] hilos;

    public Vigilante(Thread[] hilos) {
        this.hilos = hilos;
    }

    @Override
    public void run() {
        while (true) {
            Log.mostrar(Fase4_Vigilante.estados(hilos));
            try {
                Thread.sleep(PERIODO_MS);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
