package ejercicios;

import estructuras.Pila;
import modelo.Operacion;

public class Ejercicio1 {

    public static Pila<Operacion> obtenerUltimasConfirmadas(Pila<Operacion> historial, int n) {
        Pila<Operacion> resultado = new Pila<>();

        // Si n <= 0 se retorna una pila vacía (el historial no se toca)
        if (n <= 0) {
            return resultado;
        }

        Pila<Operacion> auxiliar = new Pila<>();      // guarda TODO lo que sacamos del historial
        Pila<Operacion> seleccionadas = new Pila<>(); // confirmadas encontradas (quedan al revés)
        int contador = 0;

        // 1. Sacamos del historial desde el tope (de la más reciente a la más antigua)
        while (!historial.estaVacia()) {
            Operacion op = historial.desapilar();
            auxiliar.apilar(op);
            if (op.estaConfirmada() && contador < n) {
                seleccionadas.apilar(op);
                contador++;
            }
        }

        // 2. Restauramos el historial: al devolver desde la auxiliar queda en el mismo orden
        while (!auxiliar.estaVacia()) {
            historial.apilar(auxiliar.desapilar());
        }

        // 3. "seleccionadas" tiene la más reciente en el fondo; al pasarla a
        //    "resultado" se invierte y la más reciente queda en el tope
        while (!seleccionadas.estaVacia()) {
            resultado.apilar(seleccionadas.desapilar());
        }
        return resultado;
    }

    public static void ejecutar() {
        System.out.println("=========== EJERCICIO 1: Últimas operaciones confirmadas ===========");
        Pila<Operacion> historial = new Pila<>();
        // Se apila de la más antigua a la más reciente (O5 queda en el tope)
        historial.apilar(new Operacion("O1", Operacion.ANULADA));
        historial.apilar(new Operacion("O2", Operacion.CONFIRMADA));
        historial.apilar(new Operacion("O3", Operacion.CONFIRMADA));
        historial.apilar(new Operacion("O4", Operacion.ANULADA));
        historial.apilar(new Operacion("O5", Operacion.CONFIRMADA));

        System.out.println("Historial original (tope -> fondo): " + historial);
        System.out.println("n = 2  -> " + obtenerUltimasConfirmadas(historial, 2));
        System.out.println("n = 10 -> " + obtenerUltimasConfirmadas(historial, 10) + "  (hay menos de 10, se incluyen todas)");
        System.out.println("n = 0  -> " + obtenerUltimasConfirmadas(historial, 0));
        System.out.println("Historial al final (tope -> fondo): " + historial);
        System.out.println();
    }
}
