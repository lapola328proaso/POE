import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representa un mazo de cartas: 30 monstruos + 10 cartas mágicas (40 total)
 */
public class Mazo {
    private List<Carta> cartas;
    
    public Mazo() {
        this.cartas = new ArrayList<>();
        inicializarMazo();
    }
    
    /**
     * Inicializa el mazo con 30 monstruos y 10 cartas mágicas
     */
    private void inicializarMazo() {
        // Añade 30 cartas de monstruo
        agregarMonstruos();
        // Añade 10 cartas mágicas
        agregarCartasMagicas();
        // Baraja el mazo
        barajear();
    }
    
    /**
     * Añade 30 cartas de monstruo al mazo
     */
    private void agregarMonstruos() {
        cartas.add(new CartaMonstruo("Dragón de Fuego", 1800, 1500, 5));
        cartas.add(new CartaMonstruo("Bestia de Hielo", 1600, 1400, 4));
        cartas.add(new CartaMonstruo("Caballero Oscuro", 1900, 1600, 5));
        cartas.add(new CartaMonstruo("Ángel Sagrado", 1700, 1800, 5));
        cartas.add(new CartaMonstruo("Lobo de Trueno", 1500, 1300, 3));
        
        cartas.add(new CartaMonstruo("Dragón Blanco de Ojos Azules", 3000, 2500, 8));
        cartas.add(new CartaMonstruo("Mago Oscuro", 2500, 2100, 7));
        cartas.add(new CartaMonstruo("Dragón Negro de Ojos Rojos", 2400, 2000, 7));
        cartas.add(new CartaMonstruo("Cráneo Invocado", 2500, 1200, 6));
        cartas.add(new CartaMonstruo("Gaia El Caballero Feroz", 2300, 2100, 7));
        
        cartas.add(new CartaMonstruo("Kuriboh", 300, 200, 1));
        cartas.add(new CartaMonstruo("Guerrero Castor", 1200, 1500, 3));
        cartas.add(new CartaMonstruo("Imp Salvaje", 1300, 1400, 3));
        cartas.add(new CartaMonstruo("Dragón Alado Guardián", 1400, 1200, 3));
        cartas.add(new CartaMonstruo("Bestia Mística Serket", 1000, 3000, 4));
        
        cartas.add(new CartaMonstruo("Pulga Gigante", 1000, 1000, 2));
        cartas.add(new CartaMonstruo("Tortuga Cangrejo", 1000, 3000, 4));
        cartas.add(new CartaMonstruo("Pequeño Dragón", 1400, 1200, 3));
        cartas.add(new CartaMonstruo("Coraza de Dragón", 1500, 1800, 4));
        cartas.add(new CartaMonstruo("Oveja Mística", 800, 2000, 3));
        
        cartas.add(new CartaMonstruo("Maestro de Oscuridad", 2200, 1800, 6));
        cartas.add(new CartaMonstruo("Sagitario", 1800, 1500, 5));
        cartas.add(new CartaMonstruo("Guardia Real", 1400, 1800, 4));
        cartas.add(new CartaMonstruo("Espada de Destrucción Oscura", 1600, 1200, 4));
        cartas.add(new CartaMonstruo("Bestia Gusano Malvada", 2150, 1850, 6));
        
        cartas.add(new CartaMonstruo("Dragón de Tres Cuernos", 2850, 2350, 8));
        cartas.add(new CartaMonstruo("Ninja Armado", 1200, 1400, 3));
        cartas.add(new CartaMonstruo("Toro de Batalla", 2000, 1700, 5));
        cartas.add(new CartaMonstruo("Maldición del Dragón", 2000, 1500, 5));
        cartas.add(new CartaMonstruo("Titiritero Misterioso", 1400, 1300, 4));
    }
    
    /**
     * Añade 10 cartas mágicas al mazo
     */
    private void agregarCartasMagicas() {
        cartas.add(new CartaMagica("Cáliz de Avaricia", EfectoCarta.ROBAR_DOS));
        cartas.add(new CartaMagica("Recuperación Vital", EfectoCarta.RECUPERAR_LP_MUCHO));
        cartas.add(new CartaMagica("Aniquilación", EfectoCarta.DESTRUIR_MONSTRUO));
        cartas.add(new CartaMagica("Terraformación", EfectoCarta.AUMENTAR_ATK_MUCHO));
        cartas.add(new CartaMagica("Barrera Protectora", EfectoCarta.AUMENTAR_DEF));
        
        cartas.add(new CartaMagica("Robo Rápido", EfectoCarta.ROBAR_CARTA));
        cartas.add(new CartaMagica("Sanación", EfectoCarta.RECUPERAR_LP));
        cartas.add(new CartaMagica("Golpe del Rayo", EfectoCarta.DAÑAR_RIVAL_MUCHO));
        cartas.add(new CartaMagica("Potenciador de Ataque", EfectoCarta.AUMENTAR_ATK));
        cartas.add(new CartaMagica("Maldición de Daño", EfectoCarta.DAÑAR_RIVAL));
    }
    
    /**
     * Baraja el mazo
     */
    public void barajear() {
        Collections.shuffle(cartas);
    }
    
    /**
     * Roba una carta del mazo
     * @return la carta del tope
     * @throws IllegalStateException si el mazo está vacío
     */
    public Carta robarCarta() {
        if (cartas.isEmpty()) {
            throw new IllegalStateException("¡El mazo está vacío!");
        }
        return cartas.remove(0);
    }
    
    /**
     * Obtiene el número de cartas restantes
     * @return número de cartas
     */
    public int getCartasRestantes() {
        return cartas.size();
    }
    
    /**
     * Obtiene todas las cartas del mazo
     * @return lista de cartas
     */
    public List<Carta> obtenerTodasLasCartas() {
        return new ArrayList<>(cartas);
    }
    
    /**
     * Obtiene el tamaño total del mazo (debe ser 40)
     * @return total de cartas en el mazo
     */
    public int obtenerTotalCartas() {
        return 40;
    }
}
