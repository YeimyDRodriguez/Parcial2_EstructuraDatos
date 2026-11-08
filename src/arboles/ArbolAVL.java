package arboles;

public class ArbolAVL extends ArbolBB {

    // INSERTAR
    @Override
    public void insertar(int dato) {
        System.out.println("\n>> Insertar " + dato);
        super.insertar(dato); // 1. se inserta como en un ABB normal
        balancearYMostrar(); // 2. se revisa el equilibrio
    }

    // ELIMINAR (con el MENOR del subárbol DERECHO = sucesor)
    public void eliminar(int dato) {
        System.out.println("\n>> Eliminar " + dato);
        raiz = eliminar(raiz, dato);
        balancearYMostrar();
    }

    private NodoArbol eliminar(NodoArbol nodo, int dato) {
        if (nodo == null) {
            return null;
        }
        if (dato < nodo.dato) {
            nodo.izq = eliminar(nodo.izq, dato);
        } else if (dato > nodo.dato) {
            nodo.der = eliminar(nodo.der, dato);
        } else {
            if (nodo.izq == null)
                return nodo.der;
            if (nodo.der == null)
                return nodo.izq;

            // Dos hijos: el menor del subárbol derecho (lo más a la izquierda)
            NodoArbol menor = nodo.der;
            while (menor.izq != null) {
                menor = menor.izq;
            }
            System.out.println("   " + dato + " tiene dos hijos: lo reemplaza " + menor.dato
                    + " (el menor de su subárbol derecho)");
            nodo.dato = menor.dato;
            nodo.der = eliminar(nodo.der, menor.dato);
        }
        return nodo;
    }

    // BALANCEO
    /**
     * Si hay desequilibrio, muestra el árbol antes, hace las rotaciones y lo
     * muestra después.
     */
    private void balancearYMostrar() {
        if (estaDesbalanceado(raiz)) {
            System.out.println("   Árbol ANTES de balancear (hay un FE de +2 o -2):");
            imprimir();
            raiz = balancear(raiz);
            System.out.println("   Árbol DESPUÉS de balancear:");
        } else {
            System.out.println("   Todos los FE están entre -1 y +1: no se necesita rotación");
        }
        imprimir();
    }

    /** Revisa si algún nodo tiene FE de +2 o -2. */
    private boolean estaDesbalanceado(NodoArbol nodo) {
        if (nodo == null) {
            return false;
        }
        int fe = factorEquilibrio(nodo);
        return fe > 1 || fe < -1 || estaDesbalanceado(nodo.izq) || estaDesbalanceado(nodo.der);
    }

    /**
     * Recorre en postorden (de abajo hacia arriba) y rota el nodo que esté
     * desbalanceado.
     * Así siempre se corrige primero el nodo desbalanceado más profundo.
     */
    private NodoArbol balancear(NodoArbol nodo) {
        if (nodo == null) {
            return null;
        }
        nodo.izq = balancear(nodo.izq);
        nodo.der = balancear(nodo.der);

        int fe = factorEquilibrio(nodo);
        if (fe == 2) { // cargado a la derecha
            if (factorEquilibrio(nodo.der) >= 0) {
                System.out.println("   Nodo " + nodo.dato + " con FE=+2 y su hijo derecho " + nodo.der.dato
                        + " con FE=" + factorEquilibrio(nodo.der) + " -> ROTACIÓN SIMPLE DD (a la izquierda)");
                return rotarIzquierda(nodo);
            } else {
                System.out.println("   Nodo " + nodo.dato + " con FE=+2 y su hijo derecho " + nodo.der.dato
                        + " con FE=-1 -> ROTACIÓN DOBLE DI (derecha en " + nodo.der.dato
                        + " y luego izquierda en " + nodo.dato + ")");
                nodo.der = rotarDerecha(nodo.der);
                return rotarIzquierda(nodo);
            }
        }
        if (fe == -2) { // cargado a la izquierda
            if (factorEquilibrio(nodo.izq) <= 0) {
                System.out.println("   Nodo " + nodo.dato + " con FE=-2 y su hijo izquierdo " + nodo.izq.dato
                        + " con FE=" + factorEquilibrio(nodo.izq) + " -> ROTACIÓN SIMPLE II (a la derecha)");
                return rotarDerecha(nodo);
            } else {
                System.out.println("   Nodo " + nodo.dato + " con FE=-2 y su hijo izquierdo " + nodo.izq.dato
                        + " con FE=+1 -> ROTACIÓN DOBLE ID (izquierda en " + nodo.izq.dato
                        + " y luego derecha en " + nodo.dato + ")");
                nodo.izq = rotarIzquierda(nodo.izq);
                return rotarDerecha(nodo);
            }
        }
        return nodo;
    }

    private NodoArbol rotarDerecha(NodoArbol nodo) {
        NodoArbol hijo = nodo.izq;
        nodo.izq = hijo.der;
        hijo.der = nodo;
        return hijo; // el hijo sube y queda como nueva raíz del subárbol
    }

    private NodoArbol rotarIzquierda(NodoArbol nodo) {
        NodoArbol hijo = nodo.der;
        nodo.der = hijo.izq;
        hijo.izq = nodo;
        return hijo;
    }
}
