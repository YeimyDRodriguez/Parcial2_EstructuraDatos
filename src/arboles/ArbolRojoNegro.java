package arboles;

/**
 * Árbol Rojo-Negro propio. Reglas:
 *  1. Todo nodo es rojo o negro.        2. La raíz es negra.
 *  3. Un nodo rojo no puede tener hijos rojos (no hay dos rojos seguidos).
 *  4. Todo camino de un nodo a sus hojas nulas tiene la misma cantidad de nodos negros.
 * Muestra paso a paso cada inserción, los casos aplicados y el árbol con color y FE.
 */
public class ArbolRojoNegro {
    private NodoRN raiz;

    // ------------------------- INSERTAR -------------------------
    public void insertar(int dato) { //el parametro dato es le numero que sale de la cola de postorden
        System.out.println("\n>> Insertar " + dato);
        NodoRN nuevo = new NodoRN(dato); // entra ROJO por el constructor y queda suelto por el momento

        // 1. Inserción normal de ABB, recordando quién es el padre
        NodoRN padre = null; //padre va un paso atrás, simepre uno antes que el nodo actual
        NodoRN actual = raiz; // busca el lugar vacio
        while (actual != null) { //mientras no se haya llegado a un lugar vacio, sigue bajando
            padre = actual; // antes de que actual baje, el padre pasa a ser el actual
            if (dato < actual.dato) { //si el dato es menor
                actual = actual.izq; //actual baja a la izquierda
            } else if (dato > actual.dato) { 
                actual = actual.der; // si el dato es mayor pasa a la derecha
            } else {
                return; // repetido, si no es menor ni mayor es repetido hace return y termina sin insertar
            }
        }
        nuevo.padre = padre; // al salir actual es null y padre dónde hay que colgar le nuevo
        if (padre == null) {
            raiz = nuevo; // si padre es null el arbol está vacio, el nuevo es la raíz 
        } else if (dato < padre.dato) {
            padre.izq = nuevo; // si el daro es menor que el padre va como hijo izquierdo
        } else {
            padre.der = nuevo; // si no como hijo derecho 
        }

        // 2. Si el padre es rojo hay dos rojos seguidos: se debe corregir
        if (esRojo(nuevo.padre)) {
            System.out.println("   Árbol ANTES de corregir (" + dato + " y su padre "
                    + nuevo.padre.dato + " son rojos):");
            imprimir();
        }
        corregir(nuevo);
        imprimir();
    }

    private boolean esRojo(NodoRN nodo) {
        return nodo != null && nodo.color == NodoRN.ROJO; // las hojas nulas son negras
    }

    /** Corrige las reglas después de insertar el nodo x. */
    private void corregir(NodoRN x) { // x es el nodo que puede estar causando el conflicto
        while (x != raiz && esRojo(x.padre)) { //si x es la raiz no tiene padre y no hay conflicto
            NodoRN padre = x.padre; 
            NodoRN abuelo = padre.padre;

            if (padre == abuelo.izq) {               // el padre está a la IZQUIERDA del abuelo
                NodoRN tio = abuelo.der; // si el padre está a la izquierda, el tio a la derecha
                if (esRojo(tio)) { 
                    // CASO 1: tío rojo -> solo se cambian colores
                    System.out.println("   Caso 1: el tío " + tio.dato + " es ROJO -> padre " + padre.dato
                            + " y tío " + tio.dato + " pasan a NEGRO, abuelo " + abuelo.dato + " pasa a ROJO");
                    padre.color = NodoRN.NEGRO;
                    tio.color = NodoRN.NEGRO;
                    abuelo.color = NodoRN.ROJO;
                    x = abuelo; // se sigue revisando desde el abuelo
                } else {
                    if (x == padre.der) {
                        // CASO 2: tío negro y x forma un "triángulo" -> rotar a la izquierda en el padre
                        System.out.println("   Caso 2: tío NEGRO y forma de triángulo -> ROTACIÓN A LA IZQUIERDA en " + padre.dato);
                        rotarIzquierda(padre);
                        x = padre;
                        padre = x.padre;
                    }
                    // CASO 3: tío negro y x forma una "línea" -> rotar a la derecha en el abuelo
                    System.out.println("   Caso 3: tío NEGRO y forma de línea -> ROTACIÓN A LA DERECHA en " + abuelo.dato
                            + ", " + padre.dato + " pasa a NEGRO y " + abuelo.dato + " pasa a ROJO");
                    padre.color = NodoRN.NEGRO;
                    abuelo.color = NodoRN.ROJO;
                    rotarDerecha(abuelo);
                }
            } else {                                  // el padre está a la DERECHA del abuelo (simétrico)
                NodoRN tio = abuelo.izq;
                if (esRojo(tio)) {
                    System.out.println("   Caso 1: el tío " + tio.dato + " es ROJO -> padre " + padre.dato
                            + " y tío " + tio.dato + " pasan a NEGRO, abuelo " + abuelo.dato + " pasa a ROJO");
                    padre.color = NodoRN.NEGRO;
                    tio.color = NodoRN.NEGRO;
                    abuelo.color = NodoRN.ROJO;
                    x = abuelo;
                } else {
                    if (x == padre.izq) {
                        System.out.println("   Caso 2: tío NEGRO y forma de triángulo -> ROTACIÓN A LA DERECHA en " + padre.dato);
                        rotarDerecha(padre);
                        x = padre;
                        padre = x.padre;
                    }
                    System.out.println("   Caso 3: tío NEGRO y forma de línea -> ROTACIÓN A LA IZQUIERDA en " + abuelo.dato
                            + ", " + padre.dato + " pasa a NEGRO y " + abuelo.dato + " pasa a ROJO");
                    padre.color = NodoRN.NEGRO;
                    abuelo.color = NodoRN.ROJO;
                    rotarIzquierda(abuelo);
                }
            }
        }
        if (esRojo(raiz)) {
            System.out.println("   La raíz " + raiz.dato + " se pinta de NEGRO (regla 2)");
        }
        raiz.color = NodoRN.NEGRO;
    }

    // ------------------------- ROTACIONES -------------------------
    private void rotarIzquierda(NodoRN nodo) {
        NodoRN hijo = nodo.der;
        nodo.der = hijo.izq;
        if (hijo.izq != null) hijo.izq.padre = nodo;
        cambiarHijoDelPadre(nodo, hijo);
        hijo.izq = nodo;
        nodo.padre = hijo;
    }

    private void rotarDerecha(NodoRN nodo) {
        NodoRN hijo = nodo.izq;
        nodo.izq = hijo.der;
        if (hijo.der != null) hijo.der.padre = nodo;
        cambiarHijoDelPadre(nodo, hijo);
        hijo.der = nodo;
        nodo.padre = hijo;
    }

    /** El padre de "viejo" ahora apunta a "nuevo" (o "nuevo" pasa a ser la raíz). */
    private void cambiarHijoDelPadre(NodoRN viejo, NodoRN nuevo) {
        nuevo.padre = viejo.padre;
        if (viejo.padre == null) {
            raiz = nuevo;
        } else if (viejo == viejo.padre.izq) {
            viejo.padre.izq = nuevo;
        } else {
            viejo.padre.der = nuevo;
        }
    }

    // ------------------- ALTURA, FE E IMPRIMIR -------------------
    private int altura(NodoRN nodo) {
        if (nodo == null) return 0;
        return 1 + Math.max(altura(nodo.izq), altura(nodo.der));
    }

    private int factorEquilibrio(NodoRN nodo) {
        return altura(nodo.der) - altura(nodo.izq);
    }

    /** Imprime el árbol acostado mostrando color (R = rojo, N = negro) y FE. */
    public void imprimir() {
        imprimir(raiz, 0);
    }

    private void imprimir(NodoRN nodo, int nivel) {
        if (nodo != null) {
            imprimir(nodo.der, nivel + 1);
            String color = (nodo.color == NodoRN.ROJO) ? "R" : "N";
            System.out.println("      ".repeat(nivel) + nodo.dato + " (" + color + ", FE=" + factorEquilibrio(nodo) + ")");
            imprimir(nodo.izq, nivel + 1);
        }
    }
}