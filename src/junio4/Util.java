package junio4;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Util {

    private static final int NUM_EMPLEADOS = 500;
    private static final int NUM_PROYECTOS = 100;
    private static final Random RANDOM = new Random();
    private static List<Empleado> empleados; // Lista de empleados persistente

    public static List<Empleado> generarEmpleados() {
        empleados = new ArrayList<>();
        for (int i = 0; i < NUM_EMPLEADOS; i++) {
            String nombre = "Empleado" + (i + 1);
            if (RANDOM.nextBoolean()) {
                empleados.add(new Jefe(nombre));
            } else {
                boolean esLider = RANDOM.nextBoolean();
                empleados.add(new Programador(nombre, esLider));
            }
        }
        return empleados;
    }

    public static List<Proyecto> generarProyectos(Gestor gestor) throws ParseException, ProyectoExcepcion.DemasiadosProyectos, ProyectoExcepcion.NoLider {
        List<Proyecto> proyectos = new ArrayList<>();
        for (int i = 0; i < NUM_PROYECTOS; i++) {
            String nombreProyecto = "Proyecto" + (i + 1);
            Proyecto proyecto = new Proyecto(i + 1, nombreProyecto, new Fecha("2023-01-01"), 30, 5000.0);
            asignarEmpleadosAlProyecto(proyecto, empleados);
            proyectos.add(proyecto);
            gestor.addProyecto(proyecto);
        }
        return proyectos;
    }

    public static void asignarEmpleadosAlProyecto(Proyecto proyecto, List<Empleado> empleados) throws ProyectoExcepcion.DemasiadosProyectos, ProyectoExcepcion.NoLider {
        int numProgramadores = RANDOM.nextInt((Programador.MAX_PROGRAMADORES - Programador.MIN_PROGRAMADORES) + 1) + Programador.MIN_PROGRAMADORES;
        int programadoresAsignados = 0;
        boolean jefeAsignado = false;
        boolean liderAsignado = false;

        for (Empleado empleado : empleados) {
            if (empleado instanceof Jefe && !jefeAsignado) {
                empleado.asignarProyecto(proyecto);
                jefeAsignado = true;
            } else if (empleado instanceof Programador) {
                Programador programador = (Programador) empleado;
                if (programador.isLider() && !liderAsignado) {
                    programador.asignarProyecto(proyecto);
                    liderAsignado = true;
                    programadoresAsignados++;
                } else if (!programador.isLider() && programadoresAsignados < numProgramadores) {
                    programador.asignarProyecto(proyecto);
                    programadoresAsignados++;
                }
            }

            if (jefeAsignado && programadoresAsignados >= numProgramadores) {
                break;
            }
        }
    }

    public static void inicializar(Gestor gestor) throws ParseException, ProyectoExcepcion.DemasiadosProyectos, ProyectoExcepcion.NoLider {
        generarEmpleados();
        generarProyectos(gestor);
    }
}