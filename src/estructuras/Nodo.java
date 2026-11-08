package estructuras;

public class Nodo<T> {
    T dato;            // información que guarda el nodo
    Nodo<T> siguiente; // enlace al siguiente nodo

    public Nodo(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}
