package estructuras;

/**
 * Cola propia implementada con nodos enlazados (FIFO: primero en entrar, primero en salir).
 * Se encola por el "fin" y se desencola por el "frente".
 */
public class Cola<T> {
    private Nodo<T> frente; // primer elemento (el que sale)
    private Nodo<T> fin;    // último elemento (donde se agrega)
    private int tamanio;

    public Cola() {
        frente = null;
        fin = null;
        tamanio = 0;
    }

    /** Agrega un elemento al final de la cola. */
    public void encolar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (estaVacia()) {
            frente = nuevo;          // si está vacía, el nuevo es frente y fin
        } else {
            fin.siguiente = nuevo;   // se engancha detrás del último
        }
        fin = nuevo;
        tamanio++;
    }

    /** Saca y retorna el elemento del frente. */
    public T desencolar() {
        if (estaVacia()) {
            throw new RuntimeException("La cola está vacía");
        }
        T dato = frente.dato;
        frente = frente.siguiente;
        if (frente == null) {
            fin = null;              // la cola quedó vacía
        }
        tamanio--;
        return dato;
    }

    /** Retorna el elemento del frente sin sacarlo. */
    public T verFrente() {
        if (estaVacia()) {
            throw new RuntimeException("La cola está vacía");
        }
        return frente.dato;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public int tamanio() {
        return tamanio;
    }

    /** Muestra la cola desde el frente hasta el fin. */
    @Override
    public String toString() {
        if (estaVacia()) {
            return "[ cola vacía ]";
        }
        String texto = "[ ";
        Nodo<T> actual = frente;
        while (actual != null) {
            texto += actual.dato;
            if (actual.siguiente != null) {
                texto += " -> ";
            }
            actual = actual.siguiente;
        }
        return texto + " ]";
    }
}
