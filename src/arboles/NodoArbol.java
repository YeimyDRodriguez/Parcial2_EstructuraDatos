package arboles;

/** Nodo de un árbol binario (se usa en el ABB y en el AVL). */
public class NodoArbol {
    public int dato;
    public NodoArbol izq; // hijo izquierdo (valores menores)
    public NodoArbol der; // hijo derecho (valores mayores)

    public NodoArbol(int dato) {
        this.dato = dato;
        this.izq = null;
        this.der = null;
    }
}