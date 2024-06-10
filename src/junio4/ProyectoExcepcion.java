package junio4;

public interface ProyectoExcepcion {

    public static class NoLider extends Exception {
        public NoLider() {
            super();
        }

        public NoLider(String mensaje) {
            super(mensaje);
        }
    }

    public static class DemasiadosProyectos extends Exception {
        public DemasiadosProyectos() {
            super();
        }

        public DemasiadosProyectos(String mensaje) {
            super(mensaje);
        }
    }
}
