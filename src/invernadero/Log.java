package invernadero;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase de ayuda para imprimir mensajes con la hora y el nombre del hilo
 * que está ejecutando la línea. Así se ve claramente quién hace cada cosa.
 */
public class Log {

    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    public static void mostrar(String mensaje) {
        String hora = LocalTime.now().format(FORMATO);
        String hilo = Thread.currentThread().getName();
        System.out.println("[" + hora + "] [" + hilo + "] " + mensaje);
    }
}
