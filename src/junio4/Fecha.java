package junio4;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Fecha implements Serializable {
    private Date fechaInicio;
    private Date fechaFin;
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    // Constructor que solo recibe la fecha de inicio
    public Fecha(String fechaInicio) throws ParseException {
        this.fechaInicio = dateFormat.parse(fechaInicio);
    }

    // Getter para la fecha de inicio
    public Date getFechaInicio() {
        return fechaInicio;
    }

    // Setter para la fecha de inicio
    public void setFechaInicio(String fechaInicio) throws ParseException {
        this.fechaInicio = dateFormat.parse(fechaInicio);
    }

    // Getter para la fecha de finalización
    public Date getFechaFin() {
        return fechaFin;
    }

    // Setter para la fecha de finalización
    public void setFechaFin(String fechaFin) throws ParseException {
        this.fechaFin = dateFormat.parse(fechaFin);
    }

    // Método para calcular los días transcurridos entre la fecha de inicio y la fecha de finalización o la fecha actual si el proyecto no está finalizado
    public long getDiasTranscurridos() {
        // Usamos la fecha actual si no hay una fecha de finalización
        Date fechaActual = (fechaFin != null) ? fechaFin : new Date();

        // Convertimos las fechas a objetos Calendar
        Calendar inicio = Calendar.getInstance();
        inicio.setTime(fechaInicio);
        Calendar fin = Calendar.getInstance();
        fin.setTime(fechaActual);

        // Calculamos la diferencia en días
        long diasTranscurridos = 0;
        while (inicio.before(fin) || inicio.equals(fin)) {
            inicio.add(Calendar.DAY_OF_MONTH, 1);
            diasTranscurridos++;
        }

        return diasTranscurridos - 1; // Restamos 1 porque el bucle añade un día extra
    }

    @Override
    public String toString() {
        return "Fecha de inicio: " + dateFormat.format(fechaInicio) +
               ", Fecha de finalización: " + (fechaFin != null ? dateFormat.format(fechaFin) : "No finalizado");
    }
}