package junio4;

import java.util.ArrayList;
import java.util.List;

public class Programador extends Empleado implements EsLider {
    private double salarioDiario;
    private boolean esLider;
    private List<Proyecto> proyectos;

    public Programador() {
        super();
        this.proyectos = new ArrayList<>();
    }

    public Programador(int id, String nombre, double salarioDiario, boolean esLider) {
        super();
        this.salarioDiario = salarioDiario;
        this.esLider = esLider;
        this.proyectos = new ArrayList<>();
    }

    @Override
    public void asignarProyecto(Proyecto proyecto) throws NoLiderException {
        if ((esLider && proyectos.size() < 10) || (!esLider && proyectos.size() == 0)) {
            proyectos.add(proyecto);
            proyecto.agregarEmpleado(this);
        } else {
            throw new NoLiderException();
        }
    }

    @Override
    public double getSalarioDiario() {
        return esLider ? salarioDiario * 1.25 : salarioDiario;  // Plus del 25% si es líder
    }

    @Override
    public boolean isLider() {
        return esLider;
    }

    @Override
    public List<Proyecto> getProyectos() {
        return proyectos;
    }
}