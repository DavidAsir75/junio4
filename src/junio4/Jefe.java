package junio4;

import java.util.ArrayList;
import java.util.List;

public class Jefe extends Empleado {
    public static final int MAX_PROYECTOS = 3;
    private double salarioDiario = 150.0;
    private List<Proyecto> proyectos;

    public Jefe() {
        super();
        this.proyectos = new ArrayList<>();
    }

    public Jefe(String nombre) {
        super(nombre);
        this.proyectos = new ArrayList<>();
    }

    public Jefe(String nombre, double salarioDiario) {
        super(nombre);
        this.salarioDiario = salarioDiario;
        this.proyectos = new ArrayList<>();
    }

    @Override
    public void asignarProyecto(Proyecto proyecto) throws ProyectoExcepcion.DemasiadosProyectos {
        if (proyectos.size() < MAX_PROYECTOS) {
            proyectos.add(proyecto);
            proyecto.agregarEmpleado(this);
        } else {
            throw new ProyectoExcepcion.DemasiadosProyectos("Error: Este jefe ya tiene 3 proyectos asignados.");
        }
    }

    @Override
    public double getSalarioDiario() {
        return salarioDiario;
    }

    @Override
    public List<Proyecto> getProyectos() {
        return proyectos;
    }

    @Override
    public String toString() {
        return "Jefe: " + getNombre() + ", Salario Diario: " + getSalarioDiario() + ", Proyectos Asignados: " + proyectos.size();
    }
}