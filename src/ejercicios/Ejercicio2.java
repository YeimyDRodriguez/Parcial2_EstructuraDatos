package ejercicios;

import estructuras.Pila;

/**
 * EJERCICIO 2: Obtener un segmento del historial.
 * Retorna una pila nueva con las acciones entre las posiciones inicio y fin
 * (contadas desde el tope, empezando en 1), incluidos ambos extremos.
 */
public class Ejercicio2 {

    public static Pila<String> obtenerSegmento(Pila<String> original, int inicio, int fin) {
        Pila<String> resultado = new Pila<>();

        // Validaciones: posiciones fuera de rango -> pila vacía
        if (inicio < 1 || inicio > fin || fin > original.tamanio()) {
            return resultado;
        }

        Pila<String> auxiliar = new Pila<>(); // para poder restaurar la pila original
        Pila<String> segmento = new Pila<>(); // acciones del rango (quedan al revés)
        int posicion = 1;                     // posición actual contada desde el tope

        // 1. Sacamos todos los elementos y guardamos los que están en el rango
        while (!original.estaVacia()) {
            String accion = original.desapilar();
            auxiliar.apilar(accion);
            if (posicion >= inicio && posicion <= fin) {
                segmento.apilar(accion);
            }
            posicion++;
        }

        // 2. Restauramos la pila original (mismo orden)
        while (!auxiliar.estaVacia()) {
            original.apilar(auxiliar.desapilar());
        }

        // 3. Invertimos el segmento para que la acción "inicio" quede en el tope
        while (!segmento.estaVacia()) {
            resultado.apilar(segmento.desapilar());
        }
        return resultado;
    }

    public static void ejecutar() {
        System.out.println("=========== EJERCICIO 2: Segmento del historial ===========");
        Pila<String> acciones = new Pila<>();
        for (int i = 1; i <= 6; i++) {
            acciones.apilar("A" + i); // A6 queda en el tope
        }
        System.out.println("Original (tope -> fondo): " + acciones);
        System.out.println("inicio=2, fin=4 -> " + obtenerSegmento(acciones, 2, 4));
        System.out.println("inicio=3, fin=3 -> " + obtenerSegmento(acciones, 3, 3));
        System.out.println("inicio=0, fin=2 -> " + obtenerSegmento(acciones, 0, 2) + "  (inicio < 1)");
        System.out.println("inicio=4, fin=2 -> " + obtenerSegmento(acciones, 4, 2) + "  (inicio > fin)");
        System.out.println("inicio=2, fin=9 -> " + obtenerSegmento(acciones, 2, 9) + "  (fin supera el tamaño)");
        System.out.println("Original al final: " + acciones);
        System.out.println();
    }
}

