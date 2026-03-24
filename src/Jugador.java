import java.util.ArrayList;
import java.util.List;

/**
 * Representa un jugador con mano, campo de batalla y puntos de vida
 */
public class Jugador {
    private String nombre;
    private List<Carta> mano;
    private List<CartaMonstruo> campoMonstruos; // Monstruos en el campo
    private Mazo mazo;
    private int puntosVida;
    private final int LP_INICIAL = 8000;
    
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.mano = new ArrayList<>();
        this.campoMonstruos = new ArrayList<>();
        this.mazo = new Mazo();
        this.puntosVida = LP_INICIAL;
    }
    
    /**
     * Obtiene el nombre del jugador
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Roba una carta del mazo
     */
    public Carta robarCarta() {
        if (mazo.getCartasRestantes() == 0) {
            throw new IllegalStateException("¡El mazo está vacío!");
        }
        Carta carta = mazo.robarCarta();
        mano.add(carta);
        return carta;
    }
    
    /**
     * Juega una carta desde la mano
     */
    public Carta jugarCarta(int indice) {
        if (indice < 0 || indice >= mano.size()) {
            throw new IndexOutOfBoundsException("Índice de carta inválido");
        }
        return mano.remove(indice);
    }
    
    /**
     * Añade un monstruo al campo
     */
    public void invocarMonstruo(CartaMonstruo monstruo) {
        campoMonstruos.add(monstruo);
    }
    
    /**
     * Obtiene los monstruos en el campo
     */
    public List<CartaMonstruo> getCampoMonstruos() {
        return new ArrayList<>(campoMonstruos);
    }
    
    /**
     * Destruye un monstruo específico
     */
    public boolean destruirMonstruo(CartaMonstruo monstruo) {
        return campoMonstruos.remove(monstruo);
    }
    
    /**
     * Obtiene la mano del jugador
     */
    public List<Carta> getMano() {
        return new ArrayList<>(mano);
    }
    
    /**
     * Obtiene el tamaño de la mano
     */
    public int getTamanoMano() {
        return mano.size();
    }
    
    /**
     * Obtiene una carta de la mano por índice
     */
    public Carta obtenerCartaMano(int indice) {
        if (indice < 0 || indice >= mano.size()) {
            throw new IndexOutOfBoundsException("Índice de carta inválido");
        }
        return mano.get(indice);
    }
    
    /**
     * Obtiene los puntos de vida
     */
    public int getPuntosVida() {
        return puntosVida;
    }
    
    /**
     * Reduce los puntos de vida
     */
    public void recibirDano(int cantidad) {
        this.puntosVida = Math.max(0, puntosVida - cantidad);
    }
    
    /**
     * Recupera puntos de vida
     */
    public void recuperarVida(int cantidad) {
        this.puntosVida = Math.min(LP_INICIAL, puntosVida + cantidad);
    }
    
    /**
     * Verifica si el jugador ha perdido
     */
    public boolean haPerded() {
        return puntosVida <= 0 || mazo.getCartasRestantes() == 0;
    }
    
    /**
     * Obtiene las cartas restantes en el mazo
     */
    public int getCartasRestantesMazo() {
        return mazo.getCartasRestantes();
    }
    
    /**
     * Muestra la información actual del jugador
     */
    public void mostrarEstado() {
        System.out.println("\n--- " + nombre + " ---");
        System.out.println("Puntos de Vida: " + puntosVida + "/8000");
        System.out.println("Cartas en Mano: " + mano.size());
        System.out.println("Cartas en Mazo: " + mazo.getCartasRestantes());
        System.out.println("Monstruos en Campo: " + campoMonstruos.size());
    }
    
    /**
     * Muestra la mano del jugador
     */
    public void mostrarMano() {
        System.out.println("\nMano de " + nombre + " (" + mano.size() + " cartas):");
        for (int i = 0; i < mano.size(); i++) {
            System.out.println("  [" + (i + 1) + "] " + mano.get(i));
        }
    }
    
    /**
     * Muestra el campo de batalla
     */
    public void mostrarCampo() {
        System.out.println("\nCampo de " + nombre + " (" + campoMonstruos.size() + " monstruos):");
        if (campoMonstruos.isEmpty()) {
            System.out.println("  Sin monstruos en el campo");
        } else {
            for (int i = 0; i < campoMonstruos.size(); i++) {
                System.out.println("  [" + (i + 1) + "] " + campoMonstruos.get(i));
            }
        }
    }
}
