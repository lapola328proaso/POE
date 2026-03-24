import java.util.Scanner;
public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        mostrarBienvenida();
        
        System.out.print("Nombre 1: ");
        String nombre1 = scanner.nextLine().trim();
        if (nombre1.isEmpty()) nombre1 = "Nombre 1";
        
        System.out.print("Nombre 2: ");
        String nombre2 = scanner.nextLine().trim();
        if (nombre2.isEmpty()) nombre2 = "Nombre 2";
        
        Duelo duelo = new Duelo(nombre1, nombre2);
        duelo.ejecutar();
        
        scanner.close();
    }
    

    private static void mostrarBienvenida() {
        
    }
}
