/**
 * Sistema de combate para resolver ataques entre monstruos y jugadores
 */
public class SistemaCombate {
    
    /**
     * Realiza un ataque de monstruo a monstruo
     * @param atacante monstruo que ataca
     * @param defensor monstruo que defiende
     * @param jugadorDefensor el jugador poseedor del defensor
     * @return daño infligido al jugador if es resistencia en defensa
     */
    public static int atacarMonstruo(CartaMonstruo atacante, CartaMonstruo defensor, Jugador jugadorDefensor) {
        int atkAtacante = atacante.getAtkActual();
        int defDefensor = defensor.getDef();
        
        System.out.println("\nCOMBATE: " + atacante.getNombre() + " [ATK: " + atkAtacante + "] vs " + 
                          defensor.getNombre() + " [DEF: " + defDefensor + "]");
        
        if (atkAtacante > defDefensor) {
            int diferencia = atkAtacante - defDefensor;
            System.out.println(atacante.getNombre() + " ha destruido a " + defensor.getNombre() + "!");
            System.out.println("Daño al jugador: " + diferencia);
            
            jugadorDefensor.destruirMonstruo(defensor);
            
            return diferencia;
        } else {
            System.out.println(defensor.getNombre() + " ha resistido el ataque!");
            return 0;
        }
    }
    
    /**
     * Realiza un ataque directo al jugador
     * @param atacante monstruo que ataca
     * @param jugadorDefensor jugador atacado
     */
    public static void ataqueDirecto(CartaMonstruo atacante, Jugador jugadorDefensor) {
        int damno = atacante.getAtkActual();
        System.out.println("\nATAQUE DIRECTO: " + atacante.getNombre() + " ataca a " + 
                        jugadorDefensor.getNombre() + " por " + damno + " de daño!");
        jugadorDefensor.recibirDano(damno);
    }
}
