package invernadero;

/**
 * Programador de riego. Este SÍ es un hilo (extends Thread), igual que el Cohete del Ejemplo 0A.
 * El nombre del hilo se pone llamando al constructor de Thread con super("riego").
 */
public class ProgramadorRiego extends Thread {

    public static final int CICLOS_POR_DEFECTO = 5;
    private static final int PERIODO_MS = 700;
    private static final double LITROS_POR_RIEGO = 2.5;

    private int ciclos;
    private int ciclosHechos = 0;
    private double litros = 0;

    public ProgramadorRiego() {
        this(CICLOS_POR_DEFECTO);
    }

    public ProgramadorRiego(int ciclos) {
        super("riego");
        this.ciclos = ciclos;
    }

    @Override
    public void run() {
        boolean abierta = false;
        for (int i = 1; i <= ciclos; i++) {
            abierta = !abierta; // ciclo 1 abre, ciclo 2 cierra, ciclo 3 abre...
            ciclosHechos++;
            if (abierta) {
                litros += LITROS_POR_RIEGO;
                Log.mostrar("Riego ciclo " + i + ": válvula ABIERTA  (+" + LITROS_POR_RIEGO + " L, total " + litros + " L)");
            } else {
                Log.mostrar("Riego ciclo " + i + ": válvula CERRADA");
            }

            try {
                Thread.sleep(PERIODO_MS);
            } catch (InterruptedException e) {
                Log.mostrar("Riego interrumpido");
                return;
            }
        }
    }

    public String informe() {
        return String.format("%-12s ciclos=%d  litros=%.1f L", "Riego", ciclosHechos, litros);
    }
}
