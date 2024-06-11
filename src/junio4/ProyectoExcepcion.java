package junio4;

public class ProyectoExcepcion extends Exception {
    public ProyectoExcepcion(String mensaje) {
        super(mensaje);
    }

    public static class DemasiadosProyectos extends ProyectoExcepcion {
        public DemasiadosProyectos(String mensaje) {
            super(mensaje);
        }
    }

    public static class NoLider extends ProyectoExcepcion {
        public NoLider(String mensaje) {
            super(mensaje);
        }
    }
}