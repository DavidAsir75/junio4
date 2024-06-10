package junio4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Gestor {
    private Map<Integer, Proyecto> proyectos;

    public Gestor() {
        proyectos = new HashMap<>();
    }

    // Método para añadir un proyecto al gestor
    public boolean addProyecto(Proyecto p) {
        if (proyectos.containsKey(p.getId())) {
            return false;
        }
        proyectos.put(p.getId(), p);
        return true;
    }

    // Método para obtener un proyecto por su ID
    public Proyecto getProyecto(int id) {
        return proyectos.get(id);
    }

    // Método para obtener la lista de todos los proyectos
    public List<Proyecto> getListaProyectos() {
        return new ArrayList<>(proyectos.values());
    }

    // Método para eliminar un proyecto por su ID
    public boolean removeProyecto(int id) {
        if (proyectos.containsKey(id)) {
            proyectos.remove(id);
            return true;
        }
        return false;
    }

    // Método para actualizar un proyecto existente
    public boolean updateProyecto(int id, Proyecto nuevoProyecto) {
        if (proyectos.containsKey(id)) {
            proyectos.put(id, nuevoProyecto);
            return true;
        }
        return false;
    }
}
