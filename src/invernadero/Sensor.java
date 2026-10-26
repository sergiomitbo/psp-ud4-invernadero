package invernadero;

/**
 * Sensor genérico del invernadero.
 * Es una TAREA (implements Runnable), no un hilo: igual que el Cocinero del Ejemplo 0B.
 * Para que se ejecute en paralelo hay que meterlo dentro de un Thread.
 */
public abstract class Sensor implements Runnable {

    public static final int NUM_LECTURAS = 5;

    // Atributos que pide el enunciado
    protected String nombre;
    protected String unidad;
    protected int periodoMs;
    protected int numeroLecturas;
    protected double umbralMinimo;
    protected double umbralMaximo;

    // Estadísticas propias de cada sensor
    private int lecturasHechas = 0;
    private int alertas = 0;
    private double minimo = Double.MAX_VALUE;
    private double maximo = -Double.MAX_VALUE;
    private double suma = 0;

    public Sensor(String nombre, String unidad, int periodoMs, double umbralMinimo, double umbralMaximo) {
        this.nombre = nombre;
        this.unidad = unidad;
        this.periodoMs = periodoMs;
        this.numeroLecturas = NUM_LECTURAS;
        this.umbralMinimo = umbralMinimo;
        this.umbralMaximo = umbralMaximo;
    }

    /** Cada sensor concreto genera su valor a su manera. */
    public abstract double medir();

    @Override
    public void run() {
        for (int i = 1; i <= numeroLecturas; i++) {
            double valor = medir();
            actualizarEstadisticas(valor);

            String mensaje = nombre + " lectura " + i + ": " + String.format("%.1f", valor) + " " + unidad;
            if (valor < umbralMinimo || valor > umbralMaximo) {
                alertas++;
                mensaje += "  ¡ALERTA! fuera de rango (" + umbralMinimo + " - " + umbralMaximo + ")";
            }
            Log.mostrar(mensaje);

            try {
                Thread.sleep(periodoMs);
            } catch (InterruptedException e) {
                Log.mostrar(nombre + " interrumpido");
                return;
            }
        }
    }

    private void actualizarEstadisticas(double valor) {
        lecturasHechas++;
        suma += valor;
        if (valor < minimo) minimo = valor;
        if (valor > maximo) maximo = valor;
    }

    /** Devuelve una línea con el resumen del sensor. */
    public String informe() {
        if (lecturasHechas == 0) {
            return nombre + ": 0 lecturas (todavía no ha medido nada)";
        }
        double media = suma / lecturasHechas;
        return String.format("%-12s lecturas=%d  media=%.1f %s  min=%.1f  max=%.1f  alertas=%d",
                nombre, lecturasHechas, media, unidad, minimo, maximo, alertas);
    }

    public String getNombre() {
        return nombre;
    }

    public int getPeriodoMs() {
        return periodoMs;
    }
}
