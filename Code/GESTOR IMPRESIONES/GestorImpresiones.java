import java.util.ArrayDeque;
import java.util.Deque;
 
public class GestorImpresiones {
 
    // COLA: documentos pendientes. Frente = más antiguo, final = más reciente.
    // Solo se usa offerLast (entrada), pollFirst (salida) y addFirst (recuperación).
    private final Deque<String> pendientes = new ArrayDeque<>();
 
    // PILA: documentos ya impresos. La cima es el último impreso.
    // Solo se usa push (guardar) y pop (recuperar).
    private final Deque<String> historial = new ArrayDeque<>();
 
    /** Registrar documento: entra al final de la cola. */
    public void registrar(String nombre) {
        pendientes.offerLast(nombre);
        System.out.println("Registrar \"" + nombre + "\" -> entra al final de pendientes");
        mostrarEstado();
    }
 
    /** Imprimir siguiente: sale el más antiguo y se guarda en la cima del historial. */
    public void imprimirSiguiente() {
        if (pendientes.isEmpty()) {
            System.out.println("Imprimir siguiente -> no hay documentos pendientes");
            mostrarEstado();
            return;
        }
        String nombre = pendientes.pollFirst();
        historial.push(nombre);
        System.out.println("Imprimir siguiente -> imprimiendo \"" + nombre + "\"");
        mostrarEstado();
    }
 
    /** Recuperar última: la última impresión sale de la pila y vuelve al frente de la cola. */
    public void recuperarUltima() {
        if (historial.isEmpty()) {
            System.out.println("Recuperar última -> el historial está vacío");
            mostrarEstado();
            return;
        }
        String nombre = historial.pop();
        pendientes.addFirst(nombre);
        System.out.println("Recuperar última -> \"" + nombre + "\" vuelve al frente de pendientes");
        mostrarEstado();
    }
 
    // Deque.toString() muestra desde la cabeza:
    //  - pendientes: [frente ... final]
    //  - historial:  [cima ... base]
    private void mostrarEstado() {
        System.out.println("   pendientes (frente -> final): " + pendientes);
        System.out.println("   historial  (cima -> base)   : " + historial);
        System.out.println();
    }
 
    public static void main(String[] args) {
        GestorImpresiones g = new GestorImpresiones();
 
        int paso = 1;
        System.out.println("=== Paso " + paso++ + " ===");
        g.recuperarUltima();              // historial vacío: validación
 
        System.out.println("=== Paso " + paso++ + " ===");
        g.registrar("Informe.pdf");
 
        System.out.println("=== Paso " + paso++ + " ===");
        g.registrar("Tarea.docx");
 
        System.out.println("=== Paso " + paso++ + " ===");
        g.registrar("Foto.png");
 
        System.out.println("=== Paso " + paso++ + " ===");
        g.imprimirSiguiente();            // Informe.pdf
 
        System.out.println("=== Paso " + paso++ + " ===");
        g.imprimirSiguiente();            // Tarea.docx
 
        System.out.println("=== Paso " + paso++ + " ===");
        g.recuperarUltima();              // Tarea.docx vuelve al frente
 
        System.out.println("=== Paso " + paso++ + " ===");
        g.imprimirSiguiente();            // Tarea.docx otra vez
 
        System.out.println("=== Paso " + paso++ + " ===");
        g.imprimirSiguiente();            // Foto.png
 
        System.out.println("=== Paso " + paso++ + " ===");
        g.imprimirSiguiente();            // pendientes vacía: validación
    }
}