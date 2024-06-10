package junio4;

import java.text.ParseException;
import java.util.Scanner;

public class Menu {
    Scanner teclado;
    Gestor gestor;

    public Menu() {
        teclado = new Scanner(System.in);
        gestor = new Gestor();

        // Para probar
        try {
            for (int i = 0; i < 6; i++) {
                nuevoProyecto();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        listadoProyectos();
        try {
            for (int i = 0; i < 3; i++) {
                finalizarProyecto();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        informeProyectos();
        // Fin probar
    }

    // Método para solicitar los datos del proyecto
    private void solicitaDatosProyecto(Proyecto proyecto) throws ParseException {
        System.out.print("Introduzca el nombre del proyecto: ");
        proyecto.setNombre(teclado.nextLine());
        System.out.print("Introduzca la fecha de inicio (yyyy-MM-dd): ");
        String fechaInicio = teclado.nextLine();
        proyecto.getFechas().setFechaInicio(fechaInicio);
        System.out.print("Introduzca la duración típica del proyecto (días): ");
        int duracionTipica = teclado.nextInt();
        proyecto.setDuracionTipica(duracionTipica);
        System.out.print("Introduzca el coste fijo del proyecto: ");
        double costeFijo = teclado.nextDouble();
        proyecto.setCosteFijo(costeFijo);
        teclado.nextLine(); // Consumir nueva línea
    }

    // Método para crear un nuevo proyecto
    private void nuevoProyecto() throws ParseException {
        Proyecto proyecto = new Proyecto(gestor.getListaProyectos().size() + 1, "", new Fecha("1970-01-01"), 30, 5000.0);
        solicitaDatosProyecto(proyecto);
        gestor.addProyecto(proyecto);
    }

    // Método para finalizar un proyecto
    private void finalizarProyecto() throws ParseException {
        System.out.print("Introduzca el ID del proyecto a finalizar: ");
        int id = teclado.nextInt();
        teclado.nextLine(); // Consumir nueva línea
        Proyecto proyecto = gestor.getProyecto(id);
        if (proyecto != null) {
            System.out.print("Introduzca el nombre del líder del proyecto: ");
            String nombreLider = teclado.nextLine();
            Programador lider = null;
            for (Empleado empleado : proyecto.getEmpleados()) {
                if (empleado instanceof Programador && ((Programador) empleado).isLider() && empleado.getNombre().equals(nombreLider)) {
                    lider = (Programador) empleado;
                    break;
                }
            }
            if (lider != null) {
                System.out.print("Introduzca la fecha de finalización (yyyy-MM-dd): ");
                String fechaFin = teclado.nextLine();
                try {
                    proyecto.setFechaFin(fechaFin, lider);
                } catch (ProyectoExcepcion.NoLider e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("El líder del proyecto no se encontró o no tiene permiso para finalizar el proyecto.");
            }
        } else {
            System.out.println("Proyecto no encontrado.");
        }
    }

    // Método para listar todos los proyectos
    private void listadoProyectos() {
        System.out.println("Listado de proyectos:");
        for (Proyecto proyecto : gestor.getListaProyectos()) {
            System.out.println(proyecto);
        }
    }

    // Método para generar un informe de costos de los proyectos
    private void informeProyectos() {
        System.out.println("Informe de proyectos:");
        for (Proyecto proyecto : gestor.getListaProyectos()) {
            double coste = Coste.getCoste(proyecto);
            System.out.println(proyecto + ", Costo: " + coste + "€");
        }
    }
}