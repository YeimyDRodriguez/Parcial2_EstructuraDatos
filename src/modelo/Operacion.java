package modelo;

/** Operación del historial (Ejercicio 1): tiene un código y un estado. */
public class Operacion {
    public static final String CONFIRMADA = "CONFIRMADA";
    public static final String ANULADA = "ANULADA";

    private String codigo;
    private String estado;

    public Operacion(String codigo, String estado) {
        this.codigo = codigo;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getEstado() {
        return estado;
    }

    public boolean estaConfirmada() {
        return estado.equals(CONFIRMADA);
    }

    @Override
    public String toString() {
        return codigo + "(" + estado + ")";
    }
}