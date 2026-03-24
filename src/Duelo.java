import java.util.Random;
import java.util.Scanner;

/**
 * Controla el flujo del duelo entre dos jugadores
 * Implementa RF2 (Mecánica de Turnos) y RF3 (Sistema de Combate)
 */
public class Duelo {
    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador jugadorActual;
    private Jugador jugadorRival;
    private int numeroTurno;
    private boolean esRondaInicial;
    private Scanner scanner;
    
    private final int CARTAS_INICIALES = 5;
    private final int CARTAS_ROBAR_TURNO = 1;
    
    public Duelo(String nombre1, String nombre2) {
        this.jugador1 = new Jugador(nombre1);
        this.jugador2 = new Jugador(nombre2);
        this.numeroTurno = 0;
        this.esRondaInicial = true;
        this.scanner = new Scanner(System.in);
        
        inicializarDuelo();
    }
    
    /**
     * Inicializa el duelo: distribuye cartas iniciales y determina quién empieza
     */
    private void inicializarDuelo() {
        System.out.println("\n--- INICIALIZANDO DUELO ---");
        
        // Distribuir cartas iniciales
        System.out.println("\nDistribuyendo " + CARTAS_INICIALES + " cartas a cada jugador...");
        for (int i = 0; i < CARTAS_INICIALES; i++) {
            jugador1.robarCarta();
            jugador2.robarCarta();
        }
        
        // Determinar quién empieza al azar
        Random rand = new Random();
        if (rand.nextBoolean()) {
            jugadorActual = jugador1;
            jugadorRival = jugador2;
        } else {
            jugadorActual = jugador2;
            jugadorRival = jugador1;
        }
        
        System.out.println("\n" + jugadorActual.getNombre() + " comienza el duelo.");
        pausa();
    }
    
    /**
     * Ejecuta el duelo completo
     */
    public void ejecutar() {
        while (!haDeterminadoGanador()) {
            ejecutarTurno();
            esRondaInicial = false;
            cambiarTurno();
        }
        
        mostrarResultadoFinal();
    }
    
    /**
     * Ejecuta un turno completo
     */
    private void ejecutarTurno() {
        numeroTurno++;
        
        System.out.println("\n===================================================");
        System.out.println("TURNO " + numeroTurno + " - " + jugadorActual.getNombre());
        System.out.println("===================================================");
        
        // Fase de Robo
        faseRobo();
        
        // Mostrar estado actual
        mostrarEstadoJuego();
        
        // Fase Principal: Jugar una carta
        fasePrincipal();
        
        // Fase de Ataque
        if (!jugadorActual.getCampoMonstruos().isEmpty()) {
            faseAtaque();
        } else {
            System.out.println("\n⚠️ No tienes monstruos para atacar.");
        }
        
        pausa();
    }
    
    /**
     * Fase de Robo: el jugador roba una carta del mazo
     */
    private void faseRobo() {
        System.out.println("\n--- FASE DE ROBO ---");
        try {
            Carta cartaRobada = jugadorActual.robarCarta();
            System.out.println(jugadorActual.getNombre() + " ha robado: " + cartaRobada.getNombre());
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
            System.out.println(jugadorActual.getNombre() + " PIERDE POR FALTA DE CARTAS");
        }
    }
    
    /**
     * Fase Principal: Jugar una carta
     */
    private void fasePrincipal() {
        System.out.println("\n--- FASE PRINCIPAL ---");
        System.out.println("¿Deseas jugar una carta?");
        System.out.println("1. Jugar una carta");
        System.out.println("2. Pasar");
        
        int opcion = leerOpcion(2);
        
        if (opcion == 1) {
            jugadorActual.mostrarMano();
            
            System.out.print("\nSelecciona el número de carta a jugar (0 para cancelar): ");
            int indice = leerOpcion(0, jugadorActual.getTamanoMano()) - 1;
            
            if (indice >= 0) {
                    Carta cartaJugada = jugadorActual.jugarCarta(indice);
                    
                    if (cartaJugada instanceof CartaMonstruo) {
                        CartaMonstruo monstruo = (CartaMonstruo) cartaJugada;
                        jugadorActual.invocarMonstruo(monstruo);
                        System.out.println(jugadorActual.getNombre() + " ha invocado: " + monstruo.getNombre());
                } else if (cartaJugada instanceof CartaMagica) {
                        CartaMagica magia = (CartaMagica) cartaJugada;
                        System.out.println(jugadorActual.getNombre() + " ha jugado: " + magia.getNombre());
                        ejecutarEfectoMagia(magia);
                    }
            }
        }
    }
    
    /**
     * Ejecuta el efecto de una carta mágica
     */
    private void ejecutarEfectoMagia(CartaMagica carta) {
        EfectoCarta efecto = carta.getEfecto();
        
        switch (efecto) {
            case ROBAR_CARTA:
                System.out.println("Robando 1 carta...");
                try {
                    jugadorActual.robarCarta();
                } catch (IllegalStateException e) {
                    System.out.println(e.getMessage());
                }
                break;
                
            case ROBAR_DOS:
                System.out.println("Robando 2 cartas...");
                try {
                    jugadorActual.robarCarta();
                    jugadorActual.robarCarta();
                } catch (IllegalStateException e) {
                    System.out.println(e.getMessage());
                }
                break;
                
            case RECUPERAR_LP:
                System.out.println("Recuperando 1000 LP...");
                jugadorActual.recuperarVida(1000);
                System.out.println(jugadorActual.getNombre() + " ahora tiene " + jugadorActual.getPuntosVida() + " LP");
                break;
                
            case RECUPERAR_LP_MUCHO:
                System.out.println("Recuperando 2000 LP...");
                jugadorActual.recuperarVida(2000);
                System.out.println(jugadorActual.getNombre() + " ahora tiene " + jugadorActual.getPuntosVida() + " LP");
                break;
                
            case DESTRUIR_MONSTRUO:
                System.out.println("Selecciona un monstruo rival para destruir:");
                seleccionarYDestruirMonstruo();
                break;
                
            case AUMENTAR_ATK:
                System.out.println("Selecciona un monstruo propio para aumentar ATK (+500):");
                seleccionarYAumentarATK(500, 1);
                break;
                
            case AUMENTAR_ATK_MUCHO:
                System.out.println("Selecciona un monstruo propio para aumentar ATK (+1000 por 2 turnos):");
                seleccionarYAumentarATK(1000, 2);
                break;
                
            case AUMENTAR_DEF:
                System.out.println("Selecciona un monstruo propio para aumentar DEF (+500):");
                seleccionarYAumentarDEF();
                break;
                
            case DAÑAR_RIVAL:
                System.out.println("Infligiendo 500 de daño al rival...");
                jugadorRival.recibirDano(500);
                System.out.println(jugadorRival.getNombre() + " ahora tiene " + jugadorRival.getPuntosVida() + " LP");
                break;
                
            case DAÑAR_RIVAL_MUCHO:
                System.out.println("Infligiendo 1000 de daño al rival...");
                jugadorRival.recibirDano(1000);
                System.out.println(jugadorRival.getNombre() + " ahora tiene " + jugadorRival.getPuntosVida() + " LP");
                break;
        }
    }
    
    /**
     * Permite seleccionar y destruir un monstruo rival
     */
    private void seleccionarYDestruirMonstruo() {
        jugadorRival.mostrarCampo();
        
        if (jugadorRival.getCampoMonstruos().isEmpty()) {
            System.out.println("El rival no tiene monstruos en el campo.");
            return;
        }
        
        System.out.print("\nSelecciona monstruo a destruir (0 para cancelar): ");
        int indice = leerOpcion(0, jugadorRival.getCampoMonstruos().size()) - 1;
        
        if (indice >= 0 && indice < jugadorRival.getCampoMonstruos().size()) {
            CartaMonstruo monstruoDestruido = jugadorRival.getCampoMonstruos().get(indice);
            jugadorRival.destruirMonstruo(monstruoDestruido);
            System.out.println(monstruoDestruido.getNombre() + " ha sido destruido!");
        }
    }
    
    /**
     * Permite seleccionar un monstruo propio y aumentar su ATK temporalmente
     */
    private void seleccionarYAumentarATK(int cantidad, int turnos) {
        jugadorActual.mostrarCampo();
        
        if (jugadorActual.getCampoMonstruos().isEmpty()) {
            System.out.println("No tienes monstruos en el campo.");
            return;
        }
        
        System.out.print("\nSelecciona monstruo a potenciar (0 para cancelar): ");
        int indice = leerOpcion(0, jugadorActual.getCampoMonstruos().size()) - 1;
        
        if (indice >= 0 && indice < jugadorActual.getCampoMonstruos().size()) {
            CartaMonstruo monstruo = jugadorActual.getCampoMonstruos().get(indice);
            monstruo.aumentarAtkTemporal(cantidad, turnos);
            System.out.println(monstruo.getNombre() + " tendra +" + cantidad + " ATK por " + turnos + " turno(s).");
        }
    }
    
    /**
     * Permite seleccionar un monstruo propio y aumentar su DEF permanentemente
     */
    private void seleccionarYAumentarDEF() {
        jugadorActual.mostrarCampo();
        
        if (jugadorActual.getCampoMonstruos().isEmpty()) {
            System.out.println("No tienes monstruos en el campo.");
            return;
        }
        
        System.out.print("\nSelecciona monstruo a proteger (0 para cancelar): ");
        int indice = leerOpcion(0, jugadorActual.getCampoMonstruos().size()) - 1;
        
        if (indice >= 0 && indice < jugadorActual.getCampoMonstruos().size()) {
            CartaMonstruo monstruo = jugadorActual.getCampoMonstruos().get(indice);
            monstruo.aumentarDef(500);
            System.out.println(monstruo.getNombre() + " ahora tiene +500 DEF.");
        }
    }
    
    /**
     * Fase de Ataque: el jugador declara ataques
     */
    private void faseAtaque() {
        System.out.println("\n--- FASE DE ATAQUE ---");
        
        boolean puedeAtacar = true;
        if (esRondaInicial && numeroTurno == 1) {
            System.out.println("No puedes atacar en tu primer turno de la partida.");
            puedeAtacar = false;
        }
        
        if (!puedeAtacar) return;
        
        boolean continuarAtacando = true;
        while (continuarAtacando && !jugadorActual.getCampoMonstruos().isEmpty()) {
            jugadorActual.mostrarCampo();
            jugadorRival.mostrarCampo();
            
            System.out.println("\n1. Atacar con un monstruo");
            System.out.println("2. Pasar fase de ataque");
            
            int opcion = leerOpcion(2);
            
            if (opcion == 2) {
                continuarAtacando = false;
            } else {
                System.out.print("\nSelecciona monstruo atacante (número): ");
                int indiceAtacante = leerOpcion(1, jugadorActual.getCampoMonstruos().size()) - 1;
                
                CartaMonstruo atacante = jugadorActual.getCampoMonstruos().get(indiceAtacante);
                
                if (jugadorRival.getCampoMonstruos().isEmpty()) {
                    // Ataque directo
                    System.out.println("\n¿Atacar directamente? (1=Sí, 2=No)");
                    int confirmar = leerOpcion(2);
                    if (confirmar == 1) {
                        SistemaCombate.ataqueDirecto(atacante, jugadorRival);
                        continuarAtacando = false;
                    }
                } else {
                    // Ataque a monstruo
                    jugadorRival.mostrarCampo();
                    System.out.print("\nSelecciona monstruo a atacar (número): ");
                    int indiceDefensor = leerOpcion(1, jugadorRival.getCampoMonstruos().size()) - 1;
                    
                    CartaMonstruo defensor = jugadorRival.getCampoMonstruos().get(indiceDefensor);
                    int danoRemanente = SistemaCombate.atacarMonstruo(atacante, defensor, jugadorRival);
                    
                    if (danoRemanente > 0) {
                        jugadorRival.recibirDano(danoRemanente);
                        System.out.println("✓ " + jugadorRival.getNombre() + " recibe " + danoRemanente + " de daño.");
                    }
                    continuarAtacando = false; // Cada monstruo solo ataca una vez
                }
            }
        }
    }
    
    /**
     * Muestra el estado actual del juego
     */
    private void mostrarEstadoJuego() {
        System.out.println("\n--- ESTADO DEL JUEGO ---");
        System.out.println(jugador1.getNombre() + ": " + jugador1.getPuntosVida() + " LP | Cartas en mano: " + jugador1.getTamanoMano());
        System.out.println(jugador2.getNombre() + ": " + jugador2.getPuntosVida() + " LP | Cartas en mano: " + jugador2.getTamanoMano());
    }
    
    /**
     * Cambia el turno al otro jugador
     */
    private void cambiarTurno() {
        Jugador temp = jugadorActual;
        jugadorActual = jugadorRival;
        jugadorRival = temp;
        
        // Resetear bonificaciones temporales de ATK
        for (CartaMonstruo monstruo : jugadorActual.getCampoMonstruos()) {
            monstruo.reducirTurnoBonus();
        }
    }
    
    /**
     * Verifica si hay un ganador
     */
    private boolean haDeterminadoGanador() {
        if (jugador1.haPerded()) {
            return true;
        }
        if (jugador2.haPerded()) {
            return true;
        }
        return false;
    }
    
    /**
     * Muestra el resultado final del duelo
     */
    private void mostrarResultadoFinal() {
        System.out.println("\n===================================================");
        System.out.println("FIN DEL DUELO");
        System.out.println("===================================================");
        
        if (jugador1.haPerded() && !jugador2.haPerded()) {
            System.out.println("\n" + jugador2.getNombre() + " ES EL GANADOR!");
            System.out.println("\n" + jugador1.getNombre() + " ha sido derrotado.");
            if (jugador1.getPuntosVida() <= 0) {
                System.out.println("Razon: Puntos de vida reducidos a 0");
            } else {
                System.out.println("Razon: Sin cartas en el mazo");
            }
        } else if (jugador2.haPerded() && !jugador1.haPerded()) {
            System.out.println("\n" + jugador1.getNombre() + " ES EL GANADOR!");
            System.out.println("\n" + jugador2.getNombre() + " ha sido derrotado.");
            if (jugador2.getPuntosVida() <= 0) {
                System.out.println("Razon: Puntos de vida reducidos a 0");
            } else {
                System.out.println("Razon: Sin cartas en el mazo");
            }
        }
        
        System.out.println("\n===================================================");
    }
    
    /**
     * Lee una opción del usuario entre 1 y max
     */
    private int leerOpcion(int max) {
        return leerOpcion(1, max);
    }
    
    /**
     * Lee una opción del usuario entre min y max
     */
    private int leerOpcion(int min, int max) {
        int opcion = -1;
        while (opcion < min || opcion > max) {
            System.out.print("Selecciona una opción (" + min + "-" + max + "): ");
            try {
                opcion = Integer.parseInt(scanner.nextLine());
                if (opcion < min || opcion > max) {
                    System.out.println("❌ Opción inválida. Intenta de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida. Debes ingresar un número.");
            }
        }
        return opcion;
    }
    
    /**
     * Pausa la ejecución para que el usuario pueda leer
     */
    private void pausa() {
        System.out.print("\nPresiona Enter para continuar...");
        scanner.nextLine();
    }
}
