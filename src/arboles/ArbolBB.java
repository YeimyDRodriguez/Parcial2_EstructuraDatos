package arboles;

import estructuras.Cola;

public class ArbolBB {
    protected NodoArbol raiz;

    public ArbolBB() {
        raiz = null;
    }

    public NodoArbol getRaiz() {
        return raiz;
    }

    //  INSERTAR 
    public void insertar(int dato) {
        raiz = insertar(raiz, dato);
    }

    private NodoArbol insertar(NodoArbol nodo, int dato) {
        if (nodo == null) {
            return new NodoArbol(dato);         // encontramos el lugar vacío
        }
        if (dato < nodo.dato) {
            nodo.izq = insertar(nodo.izq, dato); // menor: baja por la izquierda
        } else if (dato > nodo.dato) {
            nodo.der = insertar(nodo.der, dato); // mayor: baja por la derecha
        }
        return nodo;                             // repetidos no se insertan
    }

    // ELIMINAR (con el MAYOR del subárbol IZQUIERDO) 
    public void eliminarConPredecesor(int dato) {
        raiz = eliminarConPredecesor(raiz, dato);
    }

    private NodoArbol eliminarConPredecesor(NodoArbol nodo, int dato) {
        if (nodo == null) {
            return null; // el dato no existe
        }
        if (dato < nodo.dato) {
            nodo.izq = eliminarConPredecesor(nodo.izq, dato);
        } else if (dato > nodo.dato) {
            nodo.der = eliminarConPredecesor(nodo.der, dato);
        } else {
            // Encontramos el nodo a eliminar
            if (nodo.izq == null) return nodo.der; // 0 hijos o solo hijo derecho
            if (nodo.der == null) return nodo.izq; // solo hijo izquierdo
            
            // Dos hijos: buscamos el mayor de la izquierda (lo más a la derecha)
            NodoArbol mayor = nodo.izq;
            while (mayor.der != null) {
                mayor = mayor.der;
            }
            nodo.dato = mayor.dato;                                  // lo copiamos aquí
            nodo.izq = eliminarConPredecesor(nodo.izq, mayor.dato);  // y lo borramos abajo
        }
        return nodo;
    }

    // ALTURA Y FACTOR DE EQUILIBRIO 
    public static int altura(NodoArbol nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + Math.max(altura(nodo.izq), altura(nodo.der));
    }

    public static int factorEquilibrio(NodoArbol nodo) {
        return altura(nodo.der) - altura(nodo.izq);
    }

    // RECORRIDOS 
    // Cada recorrido guarda los datos en una Cola propia, en el orden de visita.
    public Cola<Integer> preorden() {           // raíz - izquierda - derecha
        Cola<Integer> lista = new Cola<>();
        preorden(raiz, lista);
        return lista;
    }

    private void preorden(NodoArbol nodo, Cola<Integer> lista) {
        if (nodo != null) {
            lista.encolar(nodo.dato);
            preorden(nodo.izq, lista);
            preorden(nodo.der, lista);
        }
    }

    public Cola<Integer> inorden() {            // izquierda - raíz - derecha
        Cola<Integer> lista = new Cola<>();
        inorden(raiz, lista);
        return lista;
    }

    private void inorden(NodoArbol nodo, Cola<Integer> lista) {
        if (nodo != null) {
            inorden(nodo.izq, lista);
            lista.encolar(nodo.dato);
            inorden(nodo.der, lista);
        }
    }

    public Cola<Integer> postorden() {          // izquierda - derecha - raíz
        Cola<Integer> lista = new Cola<>();
        postorden(raiz, lista);
        return lista;
    }

    private void postorden(NodoArbol nodo, Cola<Integer> lista) {
        if (nodo != null) {
            postorden(nodo.izq, lista);
            postorden(nodo.der, lista);
            lista.encolar(nodo.dato);
        }
    }

    /** Recorrido en amplitud (por niveles) usando una Cola propia de nodos. */
    public Cola<Integer> amplitud() {
        Cola<Integer> lista = new Cola<>();
        if (raiz == null) {
            return lista;
        }
        Cola<NodoArbol> cola = new Cola<>();
        cola.encolar(raiz);
        while (!cola.estaVacia()) {
            NodoArbol actual = cola.desencolar();
            lista.encolar(actual.dato);
            if (actual.izq != null) cola.encolar(actual.izq);
            if (actual.der != null) cola.encolar(actual.der);
        }
        return lista;
    }

    // ------------------------- IMPRIMIR -------------------------
    /**
     * Imprime el árbol "acostado": la raíz queda a la izquierda,
     * el hijo derecho arriba y el hijo izquierdo abajo. Cada nodo muestra su FE.
     */
    public void imprimir() {
        if (raiz == null) {
            System.out.println("   (árbol vacío)");
        }
        imprimir(raiz, 0);
    }

    private void imprimir(NodoArbol nodo, int nivel) {
        if (nodo != null) {
            imprimir(nodo.der, nivel + 1);
            System.out.println("      ".repeat(nivel) + nodo.dato + " (FE=" + factorEquilibrio(nodo) + ")");
            imprimir(nodo.izq, nivel + 1);
        }
    }
}
