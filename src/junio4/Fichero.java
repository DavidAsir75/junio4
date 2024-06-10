package junio4;

import java.io.*;
import java.util.List;

public class Fichero {

    private static final String FILENAME = "proyectos.dat";

    // Método para guardar la lista de proyectos en el archivo
    public static void guardarProyectos(List<Proyecto> proyectos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            oos.writeObject(proyectos);
        } catch (IOException e) {
            System.err.println("Error al guardar los proyectos: " + e.getMessage());
        }
    }

    // Método para leer la lista de proyectos desde el archivo
   
    public static List<Proyecto> leerProyectos() {
        List<Proyecto> proyectos = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            proyectos = (List<Proyecto>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado. Se creará un nuevo archivo.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al leer los proyectos: " + e.getMessage());
        }
        return proyectos;
    }
}