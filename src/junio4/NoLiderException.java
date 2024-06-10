package junio4;

public class NoLiderException extends Exception {
    public NoLiderException() {
        super("Error: Este programador ya tiene el máximo de proyectos asignados o no es líder.");
    }
}
