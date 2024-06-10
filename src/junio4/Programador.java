package junio4;

import java.util.ArrayList;
import java.util.List;

public class Programador extends Empleado implements EsLider {
    private static final double SALARIO_DIARIO = 100.0;
    private boolean esLider;
    private List<Proyecto> proyectos;

    public Programador() {
        super();
        this.proyectos = new ArrayList<>();
    }

    public Programador(String nombre, boolean esLider) {
        super(nombre);
        this.esLider = esLider;
        this.proyectos = new ArrayList<>();
    }

    @Override
    public boolean isLider() {
        return esLider;
    }

    @Override
    public void asignarProyecto(Proyecto proyecto) throws ProyectoExcepcion.DemasiadosProyectos, ProyectoExcepcion.NoLider {
        if (esLider && proyectos.size() >= 10) {
            throw new ProyectoExcepcion.NoLider("Un líder no puede estar en más de 10 proyectos simultáneamente.");
        } else if (!esLider && proyectos.size() >= Jefe.MAX_PROYECTOS) {
            throw new ProyectoExcepcion.DemasiadosProyectos("Un programador no puede estar en más de " + Jefe.MAX_PROYECTOS + " proyectos simultáneamente.");
        }
        proyectos.add(proyecto);
    }

    @Override
    public double getSalarioDiario() {
        if (esLider) {
            return SALARIO_DIARIO + SALARIO_DIARIO * 0.25;
        } else {
            return SALARIO_DIARIO;
        }
    }

    @Override
    public List<Proyecto> getProyectos() {
        return proyectos;
    }
}