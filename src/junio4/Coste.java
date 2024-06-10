package junio4;

public class Coste {
    // Método para calcular el costo total del proyecto
    public static double getCoste(Proyecto proyecto) {
        long diasTrabajados = proyecto.getFechas().getDiasTranscurridos();
        if (diasTrabajados <= proyecto.getDuracionTipica()) {
            return proyecto.getCosteFijo(); // Coste fijo si el proyecto dura menos o igual a la duración típica
        } else {
            double costeTotal = proyecto.getCosteFijo();
            long diferenciaDiasExtra = diasTrabajados - proyecto.getDuracionTipica();
            double costeExtra = diferenciaDiasExtra * getCostePorEmpleadoPorDia(proyecto);
            return costeTotal + costeExtra;
        }
    }

    // Método para calcular el coste por día de los empleados del proyecto
    private static double getCostePorEmpleadoPorDia(Proyecto proyecto) {
        double costeTotalPorDia = 0;
        for (Empleado empleado : proyecto.getEmpleados()) {
            costeTotalPorDia += empleado.getSalarioDiario();
        }
        return costeTotalPorDia;
    }
}