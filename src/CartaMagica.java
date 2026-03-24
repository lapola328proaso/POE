/**
 * Carta mágica con un efecto que se activa inmediatamente al jugarse
 */
public class CartaMagica extends Carta {
    private EfectoCarta efecto;
    
    public CartaMagica(String nombre, EfectoCarta efecto) {
        super(nombre);
        this.efecto = efecto;
    }
    
    public EfectoCarta getEfecto() {
        return efecto;
    }
    
    /**
     * Activa el efecto de la carta mágica
     */
    public void activar() {
        System.out.println("Carta Magica activada: " + nombre + " - " + efecto.getDescripcion());
    }
    
    @Override
    public String toString() {
        return String.format("%s [Mágica: %s]", nombre, efecto.getDescripcion());
    }
}
