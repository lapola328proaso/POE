/**
 * Clase base abstracta para todos los tipos de cartas
 */
public abstract class Carta {
    protected String nombre;
    
    public Carta(String nombre) {
        this.nombre = nombre;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    @Override
    public String toString() {
        return nombre;
    }
}
