package arboles;

/** Nodo del árbol Rojo-Negro: además de los hijos guarda el padre y el color. */
public class NodoRN {
    public static final boolean ROJO = true;
    public static final boolean NEGRO = false;

    int dato;
    boolean color;
    NodoRN izq, der, padre; //el padre se necesita para corregir, es una "flecha" que apunta hacia arriba el nodo que cuelga

    public NodoRN(int dato) {
        this.dato = dato;
        this.color = ROJO; // todo nodo nuevo entra ROJO
    }
}