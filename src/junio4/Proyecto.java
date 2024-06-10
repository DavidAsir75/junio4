package junio4;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Proyecto implements Serializable, Comparable<Proyecto> {
    private int id;
    private String nombre;
    private Fecha fechas;
    private List<Empleado> empleados;
    private Programador lider;
    private int duracionTipica; // Duración típica del proyecto en días
    private double costeFijo; // Coste fijo del proyecto

    // Constructor para inicializar el proyecto con su ID, nombre, fechas, duración típica y coste fijo
    public Proyecto(int id, String nombre, Fecha fechas, int duracionTipica, double costeFijo) {
        this.id = id;
        this.nombre = nombre;
        this.fechas = fechas;
        this.duracionTipica = duracionTipica;
        this.costeFijo = costeFijo;
        this.empleados = new ArrayList<>();
    }

    // Setter para el nombre del proyecto
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getters y setters para duración típica y coste fijo
    public int getDuracionTipica() {
        return duracionTipica;
    }

    public void setDuracionTipica(int duracionTipica) {
        this.duracionTipica = duracionTipica;
    }

    public double getCosteFijo() {
        return costeFijo;
    }

    public void setCosteFijo(double costeFijo) {
        this.costeFijo = costeFijo;
    }

    // Método para comparar proyectos por fecha de finalización
    @Override
    public int compareTo(Proyecto otroProyecto) {
        if (this.fechas.getFechaFin() == null && otroProyecto.fechas.getFechaFin() == null) {
            return 0;
        }
        if (this.fechas.getFechaFin() == null) {
            return 1; // Los proyectos sin fecha de finalización van después
        }
        if (otroProyecto.fechas.getFechaFin() == null) {
            return -1; // Los proyectos con fecha de finalización van antes
        }
        return this.fechas.getFechaFin().compareTo(otroProyecto.fechas.getFechaFin());
    }

    // Getters y setters para los atributos del proyecto
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    // Método para agregar empleados al proyecto
    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
        if (empleado instanceof Programador && ((Programador) empleado).isLider()) {
            this.lider = (Programador) empleado;
        }
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public Fecha getFechas() {
        return fechas;
    }

    public void setFechaFin(String fechaFin, Programador lider) throws ParseException, ProyectoExcepcion {
        if (this.lider == null || !this.lider.equals(lider)) {
            throw new ProyectoExcepcion("Solo el líder de programación puede establecer la fecha de finalización.");
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.fechas.setFechaFin(fechaFin);
        long diferenciaDias = (this.fechas.getFechaFin().getTime() - this.fechas.getFechaInicio().getTime()) / (1000 * 60 * 60 * 24);
        if (diferenciaDias <= this.duracionTipica) {
            this.costeFijo = this.costeFijo; // El coste fijo se mantiene
        } else {
            long diferenciaDiasExtra = diferenciaDias - this.duracionTipica;
            this.costeFijo += diferenciaDiasExtra * getCostePorEmpleadoPorDia();
        }
    }

    private double getCostePorEmpleadoPorDia() {
        double costeTotalPorDia = 0;
        for (Empleado empleado : this.empleados) {
            costeTotalPorDia += empleado.getSalarioDiario();
        }
        return costeTotalPorDia;
    }

    @Override
    public String toString() {
        return "Proyecto [id=" + id + ", nombre=" + nombre + ", fechas=" + fechas + "]";
    }
}