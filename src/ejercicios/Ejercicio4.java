package ejercicios;

/**
 * EJERCICIO 4: Árbol binario de búsqueda - consulta de equipos por rango.
 */
public class Ejercicio4 {

    /**
     * a) Cuenta cuántos códigos están en el rango [inferior, superior].
     * Aprovecha el orden del ABB para NO visitar subárboles que no pueden aportar.
     */
    public static int contarEquiposEnRango(NodoArbol nodo, int inferior, int superior) {
        // Árbol vacío o rango inválido
        if (nodo == null || inferior > superior) {
            return 0;
        }
        // El nodo es menor que el rango: todo su lado izquierdo es aún menor -> se omite
        if (nodo.dato < inferior) {
            return contarEquiposEnRango(nodo.der, inferior, superior);
        }
        // El nodo es mayor que el rango: todo su lado derecho es aún mayor -> se omite
        if (nodo.dato > superior) {
            return contarEquiposEnRango(nodo.izq, inferior, superior);
        }
        // El nodo está dentro del rango: se cuenta y se revisan ambos lados
        return 1 + contarEquiposEnRango(nodo.izq, inferior, superior)
                + contarEquiposEnRango(nodo.der, inferior, superior);
    }

    public static void ejecutar() {
        System.out.println("=========== EJERCICIO 4: ABB - Equipos por rango ===========");
        ArbolBB arbol = new ArbolBB();
        int[] codigos = {50, 30, 70, 20, 40, 60, 80, 55, 65};
        for (int codigo : codigos) {
            arbol.insertar(codigo);
        }

        System.out.println("a) Consulta por rango:");
        System.out.println("   Equipos en [35, 65] = " + contarEquiposEnRango(arbol.getRaiz(), 35, 65));
        System.out.println("   Equipos en [20, 80] = " + contarEquiposEnRango(arbol.getRaiz(), 20, 80));
        System.out.println("   Equipos en [81, 99] = " + contarEquiposEnRango(arbol.getRaiz(), 81, 99));
        System.out.println("   Equipos en [60, 40] = " + contarEquiposEnRango(arbol.getRaiz(), 60, 40) + " (inferior > superior)");
        System.out.println("   Árbol vacío         = " + contarEquiposEnRango(null, 1, 100));

        System.out.println("b) Árbol después de insertar 50, 30, 70, 20, 40, 60, 80, 55, 65:");
        arbol.imprimir();
        System.out.println("   Inorden: " + arbol.inorden());

        arbol.eliminarConPredecesor(50);
        System.out.println("   Eliminamos 50: tiene dos hijos, lo reemplaza el MAYOR de su izquierda (40).");
        System.out.println("   Árbol final:");
        arbol.imprimir();
        System.out.println("   Inorden final: " + arbol.inorden());
        System.out.println();
    }
}
dc
