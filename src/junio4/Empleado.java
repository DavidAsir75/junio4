package junio4;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public abstract class Empleado implements Serializable {
    private static int idCounter = 0;
    private int id;
    protected String nombre;

    public Empleado() {
        this.id = ++idCounter;
        this.nombre = "";
    }

    public Empleado(String nombre) {
        this.id = ++idCounter;
        this.nombre = nombre;
    }

    public abstract void asignarProyecto(Proyecto proyecto) throws NoLiderException;
    public abstract double getSalarioDiario();
    public abstract List<Proyecto> getProyectos();

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof Empleado) {
            return id == ((Empleado) o).id;
        }
        return false;
    }

    public boolean equals(Empleado e) {
        if (e != null) {
            return id == e.id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
