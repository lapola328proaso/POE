/**
 * Carta de monstruo con puntos de ataque, defensa y nivel
 */
public class CartaMonstruo extends Carta {
    private int atk;
    private int def;
    private int nivel;
    private int atkTemporal; // Para efectos temporales de cartas mágicas
    private int turnoBonusAtk; // Contador de turnos para efectos temporales
    
    public CartaMonstruo(String nombre, int atk, int def, int nivel) {
        super(nombre);
        if (nivel < 1 || nivel > 12) {
            throw new IllegalArgumentException("El nivel debe estar entre 1 y 12");
        }
        this.atk = atk;
        this.atkTemporal = atk;
        this.def = def;
        this.nivel = nivel;
        this.turnoBonusAtk = 0;
    }
    
    public int getAtk() {
        return atk;
    }
    
    public int getAtkActual() {
        return atkTemporal;
    }
    
    public void aumentarAtkTemporal(int cantidad, int turnos) {
        this.atkTemporal = atk + cantidad;
        this.turnoBonusAtk = turnos;
    }
    
    public void reducirTurnoBonus() {
        if (turnoBonusAtk > 0) {
            turnoBonusAtk--;
            if (turnoBonusAtk == 0) {
                atkTemporal = atk;
            }
        }
    }
    
    public int getDef() {
        return def;
    }
    
    public void aumentarDef(int cantidad) {
        this.def += cantidad;
    }
    
    public int getNivel() {
        return nivel;
    }
    
    public int getTurnoBonusAtk() {
        return turnoBonusAtk;
    }
    
    @Override
    public String toString() {
        if (atkTemporal != atk) {
            return String.format("%s [ATK: %d→%d, DEF: %d, Nivel: %d]", nombre, atk, atkTemporal, def, nivel);
        }
        return String.format("%s [ATK: %d, DEF: %d, Nivel: %d]", nombre, atk, def, nivel);
    }
}
