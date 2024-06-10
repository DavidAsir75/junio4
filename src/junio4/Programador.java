package junio4;

import java.util.ArrayList;
import java.util.List;

public class Programador extends Empleado {
    private static final int MIN_PROYECTOS_NORMAL = 5;
    private static final int MAX_PROYECTOS_NORMAL = 25;
    private static final int MIN_PROYECTOS_LIDER = 1;
    private static final int MAX_PROYECTOS_LIDER = 10;
    
    private double salarioDiario;
    private boolean esLider;
    private List<Proyecto> proyectos;

    public Programador() {
        super();
        this.proyectos = new ArrayList<>();
    }

    public Programador(String nombre, double salarioDiario, boolean esLider) {
        super(nombre);
        this.salarioDiario = salarioDiario;
        this.esLider = esLider;
        this.proyectos = new ArrayList<>();
    }

    @Override
    public void asignarProyecto(Proyecto proyecto) {
        if (esLider && proyectos.size() < MAX_PROYECTOS_LIDER) {
            proyectos.add(proyecto);
            proyecto.agregarEmpleado(this);
        } else if (!esLider && proyectos.size() >= MIN_PROYECTOS_NORMAL && proyectos.size() < MAX_PROYECTOS_NORMAL) {
            proyectos.add(proyecto);
            proyecto.agregarEmpleado(this);
        } else {
            System.out.println("Error: Este programador ya tiene el máximo de proyectos asignados o no cumple con el mínimo requerido.");
        }
    }

    @Override
    public double getSalarioDiario() {
        return esLider ? salarioDiario * 1.25 : salarioDiario;  // Plus del 25% si es líder
    }

    public boolean isEsLider() {
        return esLider;
    }

    @Override
    public List<Proyecto> getProyectos() {
        return proyectos;
    }
}

