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

    public abstract void asignarProyecto(Proyecto proyecto);
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empleado empleado = (Empleado) o;
        return id == empleado.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
