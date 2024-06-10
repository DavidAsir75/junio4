package junio4;

import java.text.ParseException;
import java.util.Scanner;

public class Menu {
    Scanner teclado;
    GestorProyectos gestor;

    public Menu() {
        teclado = new Scanner(System.in);
        gestor = new GestorProyectos();

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
    }

    // Método para crear un nuevo proyecto
    private void nuevoProyecto() throws ParseException {
        Proyecto proyecto = new Proyecto(gestor.getListaProyectos().size() + 1, "", new Fecha("1970-01-01"));
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
            System.out.print("Introduzca la fecha de finalización (yyyy-MM-dd): ");
            String fechaFin = teclado.nextLine();
            proyecto.setFechaFin(fechaFin);
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