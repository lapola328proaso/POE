/**
 * Enumeración de efectos posibles de cartas mágicas
 */
public enum EfectoCarta {
    ROBAR_CARTA("Roba una carta del mazo"),
    ROBAR_DOS("Roba dos cartas adicionales del mazo"),
    RECUPERAR_LP("Recupera 1000 puntos de vida"),
    RECUPERAR_LP_MUCHO("Recupera 2000 puntos de vida"),
    DESTRUIR_MONSTRUO("Destruye un monstruo rival"),
    AUMENTAR_ATK("Aumenta el ATK de un monstruo en 500 puntos por 1 turno"),
    AUMENTAR_ATK_MUCHO("Aumenta el ATK de un monstruo en 1000 puntos por 2 turnos"),
    AUMENTAR_DEF("Aumenta el DEF de un monstruo en 500 puntos"),
    DAÑAR_RIVAL("Inflige 500 de daño al rival"),
    DAÑAR_RIVAL_MUCHO("Inflige 1000 de daño al rival");
    
    private String descripcion;
    
    EfectoCarta(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
}
