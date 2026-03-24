import java.util.Scanner;

/**
 * Clase principal que inicia el juego de cartas
 */
public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        mostrarBienvenida();
        
        // Obtener nombres de los duelistas
        System.out.print("\n¿Nombre del Duelista 1: ");
        String nombre1 = scanner.nextLine().trim();
        if (nombre1.isEmpty()) nombre1 = "Duelista 1";
        
        System.out.print("¿Nombre del Duelista 2: ");
        String nombre2 = scanner.nextLine().trim();
        if (nombre2.isEmpty()) nombre2 = "Duelista 2";
        
        // Crear y ejecutar duelo
        Duelo duelo = new Duelo(nombre1, nombre2);
        duelo.ejecutar();
        
        scanner.close();
    }
    
    /**
     * Muestra el mensaje de bienvenida
     */
    private static void mostrarBienvenida() {
        System.out.println("\n================================");
        System.out.println("BIENVENIDO A DUELO DE CARTAS");
        System.out.println("================================");
        
        System.out.println("\nREGLAS BASICAS:");
        System.out.println("- Comienza con 8000 Puntos de Vida (LP)");
        System.out.println("- Inicia con 5 cartas, roba 1 cada turno");
        System.out.println("- Juega 1 carta por turno");
        System.out.println("- Los monstruos atacan 1 vez por turno");
        System.out.println("- Gana reduciendo LP rival a 0 o quedándose sin cartas");
    }
}
