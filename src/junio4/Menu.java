package junio4;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    Scanner teclado;
    Gestor gestor;

    public Menu() {
        teclado = new Scanner(System.in);
        gestor = new Gestor();

        // Leer los proyectos desde el archivo
        List<Proyecto> proyectos = Fichero.leerProyectos();
        if (proyectos != null) {
            for (Proyecto proyecto : proyectos) {
                gestor.addProyecto(proyecto);
            }
        } else {
            // Si no hay proyectos leídos del archivo, inicializamos con nuevos datos
            try {
                Util.inicializar(gestor);
            } catch (ParseException | ProyectoExcepcion.DemasiadosProyectos | ProyectoExcepcion.NoLider e) {
                e.printStackTrace();
            }
        }

        // Ejecutar el menú
        ejecutarMenu();
    }

    private void ejecutarMenu() {
        int opcion;
        do {
            mostrarMenu();
            opcion = teclado.nextInt();
            teclado.nextLine(); // Consumir nueva línea
            try {
                switch (opcion) {
                    case 1:
                        nuevoProyecto();
                        break;
                    case 2:
                        eliminarProyecto();
                        break;
                    case 3:
                        listadoProyectos();
                        break;
                    case 4:
                        informeProyectos();
                        break;
                    case 5:
                        System.out.println("Saliendo...");
                        Fichero.guardarProyectos(gestor.getListaProyectos());
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
            } catch (ParseException e) {
                System.out.println("Error al procesar la fecha: " + e.getMessage());
            }
        } while (opcion != 5);
    }

    private void mostrarMenu() {
        System.out.println("Menú:");
        System.out.println("1. Añadir proyecto");
        System.out.println("2. Borrar proyecto");
        System.out.println("3. Listado de proyectos");
        System.out.println("4. Informe de proyectos");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
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
        try {
            Util.asignarEmpleadosAlProyecto(proyecto, Util.generarEmpleados());
        } catch (ProyectoExcepcion.DemasiadosProyectos | ProyectoExcepcion.NoLider e) {
            e.printStackTrace();
        }
        gestor.addProyecto(proyecto);
    }

    // Método para eliminar un proyecto
    private void eliminarProyecto() {
        System.out.print("Introduzca el ID del proyecto a eliminar: ");
        int id = teclado.nextInt();
        teclado.nextLine(); // Consumir nueva línea
        if (gestor.removeProyecto(id)) {
            System.out.println("Proyecto eliminado correctamente.");
        } else {
            System.out.println("Proyecto no encontrado.");
        }
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
        List<Proyecto> proyectos = gestor.getListaProyectos();
        for (Proyecto proyecto : proyectos) {
            System.out.println(proyecto);
        }
    }

    // Método para generar un informe de costos de los proyectos
    private void informeProyectos() {
        System.out.println("Informe de proyectos:");
        List<Proyecto> proyectos = gestor.getListaProyectos();
        for (Proyecto proyecto : proyectos) {
            double coste = Coste.getCoste(proyecto);
            System.out.println(proyecto + ", Costo: " + coste + "€");
        }
    }
}