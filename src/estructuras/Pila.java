package estructuras;

public class Pila<T> {

    private Nodo<T> tope; // elemento de arriba
    private int tamanio;  // cantidad de elementos

    public Pila() {
        tope = null;
        tamanio = 0;
    }

    /** Agrega un elemento en el tope de la pila. */
    public void apilar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.siguiente = tope; // el nuevo apunta al antiguo tope
        tope = nuevo;           // y ahora él es el tope
        tamanio++;
    }

    /** Saca y retorna el elemento del tope. */
    public T desapilar() {
        if (estaVacia()) {
            throw new RuntimeException("La pila está vacía");
        }
        T dato = tope.dato;
        tope = tope.siguiente; // el tope baja un nodo
        tamanio--;
        return dato;
    }

    /** Retorna el elemento del tope sin sacarlo. */
    public T cima() {
        if (estaVacia()) {
            throw new RuntimeException("La pila está vacía");
        }
        return tope.dato;
    }

    public boolean estaVacia() {
        return tope == null;
    }

    public int tamanio() {
        return tamanio;
    }

    /** Muestra la pila desde el tope hasta el fondo. */
    @Override
    public String toString() {
        if (estaVacia()) {
            return "[ pila vacía ]";
        }
        String texto = "[ ";
        Nodo<T> actual = tope;
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
