package ejercicios;

import estructuras.Cola;
import modelo.Solicitud;

/**
 * EJERCICIO 3: Selección de solicitudes para una jornada.
 * Primero se atienden las de prioridad ALTA y luego las NORMAL, siempre en
 * orden de llegada, mientras quepan en los minutos disponibles.
 */
public class Ejercicio3 {

    public static Cola<Solicitud> seleccionarSolicitudes(Cola<Solicitud> pendientes, int minutosDisponibles) {
        Cola<Solicitud> seleccionadas = new Cola<>();

        // Sin tiempo disponible: cola vacía y los pendientes no cambian
        if (minutosDisponibles <= 0) {
            return seleccionadas;
        }

        int restante = minutosDisponibles;
        // Primera vuelta: solo prioridad ALTA
        restante = seleccionarPorPrioridad(pendientes, seleccionadas, Solicitud.ALTA, restante);
        // Segunda vuelta: solo prioridad NORMAL
        restante = seleccionarPorPrioridad(pendientes, seleccionadas, Solicitud.NORMAL, restante);
        return seleccionadas;
    }

    /**
     * Da UNA vuelta completa a la cola de pendientes. Cada solicitud se desencola:
     * si es de la prioridad buscada y cabe en el tiempo, pasa a "seleccionadas";
     * si no, se vuelve a encolar al final. Como se dan exactamente "tamaño" pasos,
     * los que quedan conservan su orden de llegada.
     * Retorna los minutos que quedan.
     */
    private static int seleccionarPorPrioridad(Cola<Solicitud> pendientes, Cola<Solicitud> seleccionadas,
                                               String prioridad, int restante) {
        int cantidad = pendientes.tamanio();
        for (int i = 0; i < cantidad; i++) {
            Solicitud s = pendientes.desencolar();
            if (s.getPrioridad().equals(prioridad) && s.getDuracion() <= restante) {
                seleccionadas.encolar(s);
                restante = restante - s.getDuracion(); // se descuenta su duración
            } else {
                pendientes.encolar(s); // sigue pendiente, vuelve al final
            }
        }
        return restante;
    }

    public static void ejecutar() {
        System.out.println("=========== EJERCICIO 3: Selección de solicitudes ===========");
        Cola<Solicitud> pendientes = new Cola<>();
        pendientes.encolar(new Solicitud("S1", Solicitud.NORMAL, 10));
        pendientes.encolar(new Solicitud("S2", Solicitud.ALTA, 30));
        pendientes.encolar(new Solicitud("S3", Solicitud.ALTA, 15));
        pendientes.encolar(new Solicitud("S4", Solicitud.NORMAL, 5));

        System.out.println("Pendientes iniciales: " + pendientes);
        Cola<Solicitud> seleccionadas = seleccionarSolicitudes(pendientes, 25);
        System.out.println("Con 25 minutos disponibles:");
        System.out.println("  Seleccionadas: " + seleccionadas);
        System.out.println("  Pendientes   : " + pendientes);

        Cola<Solicitud> ninguna = seleccionarSolicitudes(pendientes, 0);
        System.out.println("Con 0 minutos: seleccionadas = " + ninguna + ", pendientes = " + pendientes);
        System.out.println();
    }
}

