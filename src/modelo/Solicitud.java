package modelo;

/** Solicitud del centro de soporte (Ejercicio 3). */
public class Solicitud {
    public static final String ALTA = "ALTA";
    public static final String NORMAL = "NORMAL";

    private String codigo;
    private String prioridad; // ALTA o NORMAL
    private int duracion;     // minutos estimados

    public Solicitud(String codigo, String prioridad, int duracion) {
        this.codigo = codigo;
        this.prioridad = prioridad;
        this.duracion = duracion;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public int getDuracion() {
        return duracion;
    }

    @Override
    public String toString() {
        return codigo + "(" + prioridad + "," + duracion + "min)";
    }
}