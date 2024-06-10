package junio4;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

public class Jefe extends Empleado {
    private static final int MIN_PROYECTOS = 1;
    public static final int MAX_PROYECTOS = 3;
    private double salarioDiario;
    private List<Proyecto> proyectos;

    public Jefe() {
        super();
        this.proyectos = new ArrayList<>();
    }

    public Jefe(String nombre, double salarioDiario) {
        super(nombre);
        this.salarioDiario = salarioDiario;
        this.proyectos = new ArrayList<>();
    }

    @Override
    public void asignarProyecto(Proyecto proyecto) throws ProyectoExcepcion {
        if (proyectos.size() < MAX_PROYECTOS) {
            proyectos.add(proyecto);
            proyecto.agregarEmpleado(this);
        } else {
            throw new ProyectoExcepcion("Error: Este jefe ya tiene 3 proyectos asignados.");
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